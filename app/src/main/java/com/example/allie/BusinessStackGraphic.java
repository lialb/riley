package com.example.allie;

import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.allie.MoneyStackGraphic;

import java.text.NumberFormat;
import java.util.Locale;

public class BusinessStackGraphic extends MoneyStackGraphic {
    int verticalSpacing = 400;
    int size = 400;
    int x = (1316 - (size - 40)) / 2;
    int y = 1700;
    int height;
    Person person;

    BusinessStackGraphic(int height, Context context, Person person) {
        this.height = height;
        generateRects(height, verticalSpacing, size, x, y, -1120);
        setImages(R.drawable.skyscraper, R.drawable.shop_small, context);
        stackNum = height;
        this.context = context;
        this.person = person;
    }

    public void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", -1120, 0);

        ValueAnimator buildingRising = new ValueAnimator();
        buildingRising.setValues(duration);
        buildingRising.setDuration(4000);
        buildingRising.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rects.clear();
                generateRects(height, verticalSpacing, size, x, y, (int) animation.getAnimatedValue());
                int newY = y - verticalSpacing * stackNum - (int) animation.getAnimatedValue() + 600;
                person.teleport(person.x, newY);
            }
        });

        person.setHeight(200);
        person.teleport(-200, 1800);

        ValueAnimator walking = person.generateAnimator(2000, 550);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(walking, buildingRising);
        set.start();
    }

    public void drawText(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(70);
        paint.setTypeface(context.getResources().getFont(R.font.comfortaa_light));
        canvas.drawText("Businesses you interact with ", 50, 250, paint);
        canvas.drawText("are considered", 150, 350, paint);

        paint.setColor(context.getResources().getColor(R.color.primaryRed));
        paint.setTextSize(100);
        canvas.drawText("large enterprises", 300, 500, paint);

    }
}

package com.example.allie;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class GenderAnimation implements CanvasDrawable {
    private int maleNumber = 0, dotNumber = 0;
    private Bitmap maleIcon, femaleIcon;
    private List<Rect> rects = new ArrayList<>();
    private boolean first = true;

    private Paint paint = new Paint();

    GenderAnimation(int number, Context context) {
        final int leftX = 40, leftY = 500, rightX = 1040, rightY = 500;

        maleNumber = number;
        for (int i = 0; i < maleNumber; ++i) {
            rects.add(new Rect(leftX + 100 * i, leftY, leftX + 100 * (i + 1), leftY + 100));
        }
        for (int i = 0; i < 10 - maleNumber; ++i) {
            rects.add(new Rect(rightX - 100 * (i + 1), rightY, rightX - 100 * i, rightY + 100));
        }
        maleIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.male_icon);
        femaleIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.female_icon);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    private void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("dotNumber", 0, rects.size());

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(duration);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dotNumber = (int) animation.getAnimatedValue();
            }
        });
        animator.start();
    }
    public void draw(Canvas canvas) {
        for (int i = 0; i < dotNumber; ++i) {
            Rect maleRect = rects.get(i);
            if (i < maleNumber) {
                canvas.drawBitmap(maleIcon, null, maleRect, paint);
            }
            if (i + maleNumber < 10) {
                Rect femaleRect = rects.get(i + maleNumber);
                canvas.drawBitmap(femaleIcon, null, femaleRect, paint);
            }
        }
        if (first) {
            first = false;
            startAnimation();
        }
    }
}

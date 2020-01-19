package com.example.allie;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;

import com.example.allie.MoneyStackGraphic;

public class BusinessStackGraphic extends MoneyStackGraphic {
    int verticalSpacing = 400;
    int size = 400;
    int x = (1316 - (size - 40)) / 2;
    int y = 1700;
    int height;

    BusinessStackGraphic(int height, Context context) {
        this.height = height;
        generateRects(height, verticalSpacing, size, x, y, -1000);
        setImages(R.drawable.skyscraper, R.drawable.shop_small, context);
        stackNum = height;
        this.context = context;
    }

    public void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", -1200, 0);

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(duration);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rects.clear();
                generateRects(height, verticalSpacing, size, x, y, (int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
}

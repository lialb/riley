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
        final int leftX = 40, leftY = 50, rightX = 1312-40, rightY = 1312-30; //screen res 2560x1312
        final int gridDim = 10;
        final int imageWidth = (rightX-leftX)/gridDim;
        final int imageHieght = (rightX-leftX)/gridDim;
        int row = 0, i = 0;
        boolean alt = false;

        maleNumber = number;
        for (int count = 0; count < maleNumber; ++count) {
            if (count % gridDim == 0) {
                ++row;
                i = 0;
                alt = !alt;
            }
            if (!alt) {
                rects.add(new Rect(leftX + imageWidth * i, leftY + row * imageHieght, leftX + imageWidth * (i + 1), leftY + imageHieght * (row + 1)));
            } else {
                rects.add(new Rect(rightX - imageWidth * (i + 1), leftY + row * imageHieght, rightX - imageWidth * i, leftY + imageHieght * (row + 1)));
            }
            ++i;
        }
        row = 0;
        for (int count = 0; count < (gridDim * gridDim) - maleNumber; ++count) {
            if (count % gridDim == 0) {
                ++row;
                i = 0;
                alt = !alt;
            }
            if (!alt) {
                rects.add(new Rect(rightX - imageWidth * (i + 1), rightY - row * imageHieght, rightX - imageWidth * i, rightY - imageHieght * (row - 1)));
            } else {
                rects.add(new Rect(leftX + imageWidth * i, rightY - row * imageHieght, leftX + imageWidth * (i + 1), rightY - imageHieght * (row - 1)));
            }
            ++i;
        }
        maleIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.male_icon);
        femaleIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.female_icon);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void startAnimation() {
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
            if (i + maleNumber < 100) {
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

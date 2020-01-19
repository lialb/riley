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

    private Context context;
    private Paint paint = new Paint();

    GenderAnimation(int number, Context context) {
        int leftX = 40, leftY = 50, rightX = 1312 - 40, rightY = 1312 - 30; //screen res 2560x1312
        final int gridDim = 10;
        final int imageWidth = (rightX - leftX) / gridDim;
        final int imageHeight = (rightY - leftY) / gridDim;
        rightY += imageHeight;
        int row = 0, i = 0;
        boolean alt = false;

        this.context = context;

        maleNumber = number;
        for (int count = 0; count < maleNumber; ++count) {
            if (count % gridDim == 0) {
                ++row;
                i = 0;
                alt = !alt;
            }
            if (!alt) {
                rects.add(new Rect(leftX + imageWidth * i, leftY + row * imageHeight, leftX + imageWidth * (i + 1), leftY + imageHeight * (row + 1)));
            } else {
                rects.add(new Rect(rightX - imageWidth * (i + 1), leftY + row * imageHeight, rightX - imageWidth * i, leftY + imageHeight * (row + 1)));
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
                rects.add(new Rect(rightX - imageWidth * (i + 1), rightY - row * imageHeight, rightX - imageWidth * i, rightY - imageHeight * (row - 1)));
            } else {
                rects.add(new Rect(leftX + imageWidth * i, rightY - row * imageHeight, leftX + imageWidth * (i + 1), rightY - imageHeight * (row - 1)));
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
        int males = 0;
        int females = 0;
        for (int i = 0; i < dotNumber; ++i) {
            Rect maleRect = rects.get(i);
            if (i < maleNumber) {
                canvas.drawBitmap(maleIcon, null, maleRect, paint);
                males++;
            }
            if (i + maleNumber < 100) {
                Rect femaleRect = rects.get(i + maleNumber);
                canvas.drawBitmap(femaleIcon, null, femaleRect, paint);
                females++;
            }
        }

        drawText(canvas, males, females);

        if (first) {
            first = false;
            startAnimation();
        }
    }

    public void drawText(Canvas canvas, int male, int female) {
        paint.setColor(context.getColor(R.color.primaryLightTurq));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(250);
        paint.setTypeface(context.getResources().getFont(R.font.comfortaa_light));
        int midPoint = canvas.getWidth() / 2;
        canvas.drawText("" + male, midPoint - 320, 1900, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(":", midPoint, 1900, paint);
        paint.setColor(context.getColor(R.color.spanishPink));
        canvas.drawText("" + female, midPoint + 80, 1900, paint);

    }
}

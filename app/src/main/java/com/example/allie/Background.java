package com.example.allie;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;

public class Background {

    private Paint paint = new Paint();
    Bitmap bm;

    Rect cloud1, cloud2, cloud3;

    Background(Context context) {
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.spanishPink));
        paint.setStyle(Paint.Style.FILL);

        int x = 300;
        int y = 500;
        int size = 400;
        cloud1 = new Rect(x, y, x + size, y + size);

        x = 1000;
        y = 300;
        size = 200;
        cloud2 = new Rect(x, y, x + size, y + size);

        x = 250;
        y = 125;
        size = 150;
        cloud3 = new Rect(x, y, x + size, y + size);

        bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud);

    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
        paint.setColorFilter(new LightingColorFilter(paint.getColor(), 0));
        canvas.drawBitmap(bm, null, cloud1, paint);
        canvas.drawBitmap(bm, null, cloud2, paint);
        canvas.drawBitmap(bm, null, cloud3, paint);
    }

    public void updateColor(int colorTo) {
        int colorFrom = paint.getColor();
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(300); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                paint.setColor((int) animator.getAnimatedValue());
            }
        });

        colorAnimation.start();
    }
}

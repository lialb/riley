package com.example.allie.StatsPagerStuff;

import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.allie.CanvasDrawable;
import com.example.allie.R;

public class SpendingGraphic implements CanvasDrawable {
    Bitmap wallet;
    int walletX = 450;
    Rect walletLocation;
    private Paint paint = new Paint();
    int size = 600;


    public SpendingGraphic(int number, Context context) {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        wallet = BitmapFactory.decodeResource(context.getResources(), R.drawable.wallet);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(wallet, null, walletLocation, paint);
    }

    @Override
    public void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", 2000, 1200);

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(duration);
        animator.setDuration(500);
        animator.setInterpolator(new OvershootInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int walletY = (int) animation.getAnimatedValue();
                walletLocation = new Rect(walletX, walletY, walletX + size, walletY + size);

            }
        });
        animator.start();

    }
}

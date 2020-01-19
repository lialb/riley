package com.example.allie.StatsPagerStuff;

import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;

import com.example.allie.CanvasDrawable;
import com.example.allie.R;

public class SpendingGraphic implements CanvasDrawable {
    Bitmap wallet;
    Bitmap dollar;
    int walletX = 450;
    Rect walletLocation;
    Rect dollarLocation = new Rect(walletX, 1200, walletX + 500, 1200 + 270);
    private Paint paint = new Paint();
    int size = 600;


    public SpendingGraphic(int number, Context context) {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        wallet = BitmapFactory.decodeResource(context.getResources(), R.drawable.wallet);
        dollar = BitmapFactory.decodeResource(context.getResources(), R.drawable.dollar);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(wallet, null, walletLocation, paint);
        canvas.drawBitmap(dollar, null, dollarLocation, paint);
    }

    @Override
    public void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", 2000, 1200);

        ValueAnimator walletUpAnimation = new ValueAnimator();
        walletUpAnimation.setValues(duration);
        walletUpAnimation.setDuration(500);
        walletUpAnimation.setInterpolator(new OvershootInterpolator());
        walletUpAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int walletY = (int) animation.getAnimatedValue();
                walletLocation = new Rect(walletX, walletY, walletX + size, walletY + size);

            }
        });
        walletUpAnimation.start();

        ValueAnimator dollarFan = new ValueAnimator();

    }
}

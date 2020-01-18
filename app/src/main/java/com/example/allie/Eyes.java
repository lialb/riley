package com.example.allie;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class Eyes implements CanvasDrawable {
    private int x, y, radius;
    private Paint paint = new Paint();
    private RectF oval;

    Eyes(int xCoordinate, int yCoordinate, Context context) {
        x = xCoordinate;
        y = yCoordinate;

//        oval = new RectF(100, 100, 200, 200);

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }
    private int i = 0;
    private Timer timer = new Timer();
    private TimerTask t;
    private boolean blink = false, first = true;

    public void draw(Canvas canvas) {
         t = new TimerTask() {
            @Override
            public void run() {
                blink = !blink;
            }
         };
         if (first) {
             canvas.drawCircle(x, y, 50, paint);
             canvas.drawCircle(x + 250, y, 50, paint);
             first = false;
             timer.scheduleAtFixedRate(t,10000,10000);
         }
         blink(canvas);
         canvas.drawRect(x, y, x + 50, y + 10, paint);
         canvas.drawRect(x + 250, y, x + 300, y + 10, paint);
    }

    private void blink(Canvas canvas) {
        if (!blink) {
            canvas.drawCircle(x, y, 50, paint);
            canvas.drawCircle(x + 250, y, 50, paint);
        }
    }
}

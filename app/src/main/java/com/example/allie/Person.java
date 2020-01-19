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
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Person {

    private Bitmap img;
    private Rect rect;
    private Paint paint = new Paint();
    ValueAnimator currentAnimator = null;

    int x;
    int y;
    int width = 400;
    int height = 700;

    private List<Bitmap> frames = new ArrayList<>();
    private Bitmap standing;
    boolean isWalking = false;
    int frameIndex = 0;

    Timer walkingTimer;

    Person(int x, int y, Context context) {
        this.x = x;
        this.y = y;

        rect = new Rect(x, y, x + width, y + height);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        generateFrames(context);
    }

    public void setHeight(int height) {
        this.height = height;
        this.width = (int) ((float) height / 7) * 4;
    }

    private void generateFrames(Context context) {
        Bitmap spriteSheetEven = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_even);
        Bitmap spriteSheetOdd = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_odd);
        standing = BitmapFactory.decodeResource(context.getResources(), R.drawable.standing);

        int width = spriteSheetEven.getWidth() / 4;
        int height = spriteSheetEven.getHeight();

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                Bitmap bm = (j % 2 == 0) ? spriteSheetEven : spriteSheetOdd;
                frames.add(Bitmap.createBitmap(bm, i * width, 0, width, height));
            }
        }
    }

    public void setWalking(boolean startWalking) {
        if (!this.isWalking && startWalking) {
            isWalking = true;
            walkingTimer = new Timer();
            walkingTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    frameIndex = (frameIndex + 1) % frames.size();
                }
            }, 0, 100);
        } else if (this.isWalking && !startWalking) {
            isWalking = false;
            walkingTimer.cancel();
        }
    }

    public void teleport(int x, int y) {
        // Prevents skipping ahead from getting person to unwanted places
        clearAnimations();

        this.x = x;
        this.y = y;
        rect = new Rect(x, y, x + width, y + height);
    }

    public void move(int length, final int xCoord) {
        setWalking(true);
        generateAnimator(length, xCoord).start();
    }

    // Stops animators,
    public void clearAnimations() {
        if (currentAnimator != null) currentAnimator.end();
        setWalking(false);
    }

    public ValueAnimator generateAnimator(int length, int xCoord) {
        clearAnimations();
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", this.x, xCoord);
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(duration);
        animator.setDuration(length);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int newX = (int) animation.getAnimatedValue();
                x = newX;
                rect = new Rect(newX, y, newX + width, y + height);
                if (newX == xCoord) setWalking(false);
            }
        });
        currentAnimator = animator;
        setWalking(true);
        return animator;
    }

    public void draw(Canvas canvas) {
        if (isWalking) canvas.drawBitmap(frames.get(frameIndex), null, rect, paint);
        else canvas.drawBitmap(standing, null, rect, paint);
    }

    public void nextFrame() {
        frameIndex = (frameIndex + 1) % frames.size();
    }

    public void start() {
    }

}

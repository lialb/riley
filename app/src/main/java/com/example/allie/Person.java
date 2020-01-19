package com.example.allie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Person {

    private Bitmap img;
    private Rect rect;
    private Paint paint = new Paint();
    private AnimationDrawable person;

    private List<Bitmap> frames = new ArrayList<>();
    private Bitmap standing;
    boolean isWalking = false;
    int frameIndex = 0;

    Timer walkingTimer;

    Person(int x, int y, Context context) {
        rect = new Rect(x, y, x + 400, y + 700);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        generateFrames(context);
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
            }, 0, 200);
        } else if (this.isWalking && !startWalking) {
            isWalking = false;
            walkingTimer.cancel();
        }
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

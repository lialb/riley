package com.example.allie;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

public class Person {

    private Bitmap img;
    private Rect rect;
    private Paint paint = new Paint();
    private Arm rightArm = new Arm();
    private AnimatedVectorDrawable person;

    Person(int x, int y, Context context) {
        rect = new Rect(x, y, x + 100, y + 100);
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.materialdesign_image);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        person = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(context.getResources(), R.drawable.test_animate, null);
        person.setBounds(10, 10, 1000, 1000);
    }

    public void draw(Canvas canvas) {
//        person.draw(canvas);
    }

    public void start() {
        person.start();
    }

    public Arm getArm() {
        return rightArm;
    }

}

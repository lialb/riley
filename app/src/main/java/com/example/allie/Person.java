package com.example.allie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Person implements CanvasDrawable {

    private Bitmap img;
    private Rect rect;
    private Paint paint = new Paint();
    private Arm rightArm = new Arm();

    Person(int x, int y, Context context) {
        rect = new Rect(x, y, x + 100, y + 100);
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.materialdesign_image);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, null, rect, paint);
        rightArm.draw(canvas);
    }

    public Arm getArm() {
        return rightArm;
    }

}

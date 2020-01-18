package com.example.allie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Person implements CanvasDrawable {

    private Bitmap img;
    private Rect rect;
    private Paint paint = new Paint();

    Person(int x, int y, Context context) {
        rect = new Rect(x, y, x + 500, y + 500);
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.materialdesign_image);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, null, rect, paint);

    }
}

package com.example.allie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class LanguageGraphic implements CanvasDrawable {
    Context context;
    Person person;
    Paint paint = new Paint();
    Rect bubbleLocation;

    Bitmap bubble;

    public LanguageGraphic(Context context,Person person) {
        this.context = context;
        this.person = person;

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        bubble = BitmapFactory.decodeResource(context.getResources(), R.drawable.chat_bubble);
        bubbleLocation = new Rect(400, 300, 1300, 1100);

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bubble, null, bubbleLocation, paint);
    }

    @Override
    public void startAnimation() {
        person.setHeight(2000);
        person.teleport(-300, 1800);
    }
}

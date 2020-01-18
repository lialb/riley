package com.example.allie;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Arm implements CanvasDrawable {
    private Paint paint = new Paint();

    Arm() {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void draw(Canvas canvas) {
        Path path = new Path();
        int x = 500;
        int y = 800;
        path.moveTo(x, y);
        path.cubicTo(x, y, x + 30, y + 300, x + 400, y - 200);

        canvas.drawPath(path, paint);

    }
}

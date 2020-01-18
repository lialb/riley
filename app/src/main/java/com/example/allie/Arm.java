package com.example.allie;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;

public class Arm implements CanvasDrawable {
    private Paint paint = new Paint();

    // Absolute fields
    private float handX = 900;
    private float handY = 600;

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
        path.cubicTo(x, y, x + 30, y + 300, handX, handY);

        canvas.drawPath(path, paint);

    }

    public void moveArm(Path path) {
        final PathMeasure pm = new PathMeasure(path, false);

        // Ranges from 0 to 1, used to know what part of the path you're in
        PropertyValuesHolder duration = PropertyValuesHolder.ofFloat("arm_timer", 0, 1);

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(duration);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float aCoordinates[] = {0f, 0f};
                float armTimer = (float) animation.getAnimatedValue("arm_timer");
                pm.getPosTan(pm.getLength() * armTimer, aCoordinates, null);

                handX = aCoordinates[0];
                handY = aCoordinates[1];
            }
        });
        animator.start();
    }
}

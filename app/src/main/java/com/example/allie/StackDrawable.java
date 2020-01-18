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

import com.example.allie.CanvasDrawable;
import com.example.allie.R;

import java.util.ArrayList;
import java.util.List;

public class StackDrawable implements CanvasDrawable {
    List<Rect> rects = new ArrayList<>();
    Bitmap img;
    // Number of items to show on the stack
    int stackNum = 0;
    private Paint paint = new Paint();

    public StackDrawable(int height, int verticalSpacing, int drawableID, Context context) {
        // Hardcoded screen resolution
        int size = 600;
        int x = (1316 - (size - 40)) / 2;
        int y = 1500;

        for (int i = 0; i < height; ++i) {
            rects.add(new Rect(x, y - verticalSpacing * i,
                    x + size, y + size - verticalSpacing * i));
        }

        img = BitmapFactory.decodeResource(context.getResources(), drawableID);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", 0, rects.size());

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(duration);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
            stackNum = (int) animation.getAnimatedValue();
            }
        });
        animator.start();

    }

    public void draw(Canvas canvas) {
        System.out.println(canvas.getWidth());
        for (int i = 0; i < stackNum; ++i) {
            Rect rect = rects.get(i);
            canvas.drawBitmap(img, null, rect, paint);
        }
    }
}

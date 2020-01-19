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

import java.util.ArrayList;
import java.util.List;

public class MoneyStackGraphic implements CanvasDrawable {
    List<Rect> rects = new ArrayList<>();
    Bitmap baseImage, topImage;
    // Number of items to show on the stack
    int stackNum = 0;
    private Paint paint = new Paint();

    public MoneyStackGraphic() {};

    public MoneyStackGraphic(int height, Context context) {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        // Hardcoded screen resolution
        int verticalSpacing = 60;
        int size = 600;
        int x = 450;
        int y = 1500;

        generateRects(height, verticalSpacing, size, x, y);
        setImages(R.drawable.money_stack, R.drawable.money_stack, context);

    }

    void setImages(int base, int top, Context context) {
        baseImage = BitmapFactory.decodeResource(context.getResources(), base);
        topImage = BitmapFactory.decodeResource(context.getResources(), top);
    }

    void generateRects(int height, int verticalSpacing, int size, int x, int y) {
        for (int i = 0; i < height; ++i) {
            rects.add(new Rect(x, y - verticalSpacing * i,
                    x + size, y + size - verticalSpacing * i));
        }
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
        for (int i = 0; i < stackNum; ++i) {
            Rect rect = rects.get(i);

            Bitmap toDraw = (i != stackNum - 1) ? baseImage : topImage;
            canvas.drawBitmap(toDraw, null, rect, paint);
        }
    }
}

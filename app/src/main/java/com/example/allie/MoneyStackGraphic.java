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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MoneyStackGraphic implements CanvasDrawable {
    List<Rect> rects = new ArrayList<>();
    Bitmap baseImage, topImage;
    // Number of items to show on the stack
    int stackNum = 0;
    Paint paint = new Paint();
    Context context;
    int textDollars = 0;

    public MoneyStackGraphic() {}

    public MoneyStackGraphic(int height, Context context) {
        paint.setAntiAlias(true);
        this.context = context;

        // Hardcoded screen resolution
        int verticalSpacing = 60;
        int size = 600;
        int x = 450;
        int y = 1500;

        generateRects(height, verticalSpacing, size, x, y, 0);
        setImages(R.drawable.money_stack, R.drawable.money_stack, context);
    }

    void drawText(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(130);
        paint.setTypeface(context.getResources().getFont(R.font.comfortaa_light));
        canvas.drawText("Riley earns ", 200, 350, paint);

        paint.setColor(context.getResources().getColor(R.color.primaryLightTurq));
        paint.setTextSize(200);
        canvas.drawText("$" + NumberFormat.getNumberInstance(Locale.US).format(textDollars), 300, 550, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText("per year", 550, 650, paint);
    }

    void setImages(int base, int top, Context context) {
        baseImage = BitmapFactory.decodeResource(context.getResources(), base);
        topImage = BitmapFactory.decodeResource(context.getResources(), top);
    }

    void generateRects(int height, int verticalSpacing, int size, int x, int y, int y_offset) {
        for (int i = 0; i < height; ++i) {
            rects.add(new Rect(x, y - verticalSpacing * i - y_offset,
                    x + size, y + size - verticalSpacing * i - y_offset));
        }
    }

    public void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", 0, rects.size());
        PropertyValuesHolder dollars = PropertyValuesHolder.ofInt("dollars", 0, 64000);

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(duration, dollars);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textDollars = (int) animation.getAnimatedValue("dollars");
                stackNum = (int) animation.getAnimatedValue("stackNum");
            }
        });

        animator.start();
    }

    public void draw(Canvas canvas) {
        drawText(canvas);
        for (int i = 0; i < stackNum; ++i) {
            Rect rect = rects.get(i);

            Bitmap toDraw = (i != stackNum - 1) ? baseImage : topImage;
            canvas.drawBitmap(toDraw, null, rect, paint);
        }
    }
}

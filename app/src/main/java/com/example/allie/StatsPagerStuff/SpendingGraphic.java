package com.example.allie.StatsPagerStuff;

import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;

import com.example.allie.CanvasDrawable;
import com.example.allie.Person;
import com.example.allie.R;

import java.text.NumberFormat;
import java.util.Locale;

public class SpendingGraphic implements CanvasDrawable {
    Bitmap wallet, walletFront;
    Bitmap dollar;
    int size = 600;
    int walletX = 600;
    Rect walletLocation = new Rect(walletX, 3000, walletX + size, 3000 + size);
    Rect frontWalletLocation = new Rect(walletX, 3000, walletX + size, 3000 + size);
    Rect dollarLocation = new Rect(walletX, 1200, walletX + 500, 1200 + 270);
    private Paint paint = new Paint();
    Context context;

    Person person;

    int numBills = 5;
    int dollarsText = 0;

    float billAngle = 0;


    public SpendingGraphic(int number, Context context, Person person) {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        this.context = context;
        this.person = person;

        wallet = BitmapFactory.decodeResource(context.getResources(), R.drawable.wallet);
        walletFront = BitmapFactory.decodeResource(context.getResources(), R.drawable.cut_wallet);
        dollar = BitmapFactory.decodeResource(context.getResources(), R.drawable.dollar);
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            canvas.save();
            canvas.drawBitmap(wallet, null, walletLocation, paint);

            for (int i = 0; i < numBills; i++) {
                canvas.drawBitmap(dollar, null, dollarLocation, paint);
                canvas.rotate(billAngle, 450, 1200 + 170);
            }
            canvas.restore();

            canvas.drawBitmap(walletFront, null, walletLocation, paint);

            drawText(canvas);
        }
    }

    @Override
    public void startAnimation() {
        PropertyValuesHolder duration = PropertyValuesHolder.ofInt("stackNum", 2000, 1200);

        ValueAnimator walletUpAnimation = new ValueAnimator();
        walletUpAnimation.setValues(duration);
        walletUpAnimation.setDuration(500);
        walletUpAnimation.setInterpolator(new OvershootInterpolator());
        walletUpAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int walletY = (int) animation.getAnimatedValue("stackNum");
                walletLocation = new Rect(walletX, walletY, walletX + size, walletY + size);
                frontWalletLocation = new Rect(walletX, walletY, walletX + size, walletY + size - 100);
                dollarLocation = new Rect(walletX + 50, walletY + 80, walletX + 500 + 50, walletY + 270 + 80);

            }
        });

        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("stackNum", 0, -15);
        PropertyValuesHolder dollarsPerVisit = PropertyValuesHolder.ofInt("spendingDollars", 0, 115);
        ValueAnimator dollarFan = new ValueAnimator();
        dollarFan.setValues(rotation, dollarsPerVisit);
        dollarFan.setDuration(1000);
        dollarFan.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                billAngle = (float) animation.getAnimatedValue("stackNum");
                dollarsText = (int) animation.getAnimatedValue("spendingDollars");
            }
        });

        person.teleport(-300, 1000);
        person.setHeight(2000);
        ValueAnimator personAnimation = person.generateAnimator(500, -300);
        AnimatorSet s = new AnimatorSet();
        s.play(dollarFan).after(walletUpAnimation).after(personAnimation);
        s.start();
    }

    void drawText(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(100);
        paint.setTypeface(context.getResources().getFont(R.font.comfortaa_light));
        canvas.drawText("Riley typically spends ", 100, 350, paint);

        paint.setColor(context.getResources().getColor(R.color.primaryLightTurq));
        paint.setTextSize(200);
        canvas.drawText("$" + NumberFormat.getNumberInstance(Locale.US).format(dollarsText), 300, 550, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(85);
        canvas.drawText("per entrance", 550, 650, paint);
    }
}

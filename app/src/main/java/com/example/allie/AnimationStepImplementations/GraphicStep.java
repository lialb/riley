package com.example.allie.AnimationStepImplementations;

import android.graphics.Canvas;

import com.example.allie.Background;
import com.example.allie.CanvasDrawable;
import com.example.allie.CustomCanvasView;
import com.example.allie.Person;

public class GraphicStep extends AnimationStep {
    CanvasDrawable drawable;
    CustomCanvasView canvas;

    public GraphicStep(CanvasDrawable drawable, Person person, Background background, CustomCanvasView canvas) {
        this.drawable = drawable;
        this.canvas = canvas;
    }
    public void run() {
        canvas.addDrawable(drawable);
        drawable.startAnimation();
    }

    public CanvasDrawable getDrawable() {
        return drawable;
    }

}

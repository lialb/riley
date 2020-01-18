package com.example.allie.AnimationStepImplementations;

import com.example.allie.Background;
import com.example.allie.CanvasDrawable;
import com.example.allie.Person;

public class VisualizeAnimation extends AnimationStep {
    CanvasDrawable drawable;

    public VisualizeAnimation(CanvasDrawable drawable, Person person, Background background) {
        super(person, background);
        this.drawable = drawable;
    }
    public void run() {
        System.out.println("Ran!");
        drawable.startAnimation();
    }

}

package com.example.allie.AnimationStepImplementations;

import android.graphics.Canvas;

import com.example.allie.Background;
import com.example.allie.Person;

public abstract class AnimationStep {
    Person person;
    Background background;

    AnimationStep() {}

    AnimationStep(Person person, Background background) {
        this.person = person;
        this.background = background;
    }

    public abstract void run();
}

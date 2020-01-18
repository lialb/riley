package com.example.allie.AnimationStepImplementations;

import com.example.allie.Background;
import com.example.allie.Person;

public class BackgroundChange extends AnimationStep {
    int color;

    public BackgroundChange(int color, Person person, Background background) {
        super(person, background);
        this.color = color;
    }

    public void run() {
        background.updateColor(color);

    }
}

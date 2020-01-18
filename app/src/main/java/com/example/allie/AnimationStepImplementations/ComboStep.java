package com.example.allie.AnimationStepImplementations;

import com.example.allie.Background;
import com.example.allie.Person;

import java.util.List;

public class ComboStep extends AnimationStep {
    AnimationStep[] steps;

    public ComboStep(AnimationStep[] steps, Person person, Background background) {
        super(person, background);
        this.steps = steps;
    }

    @Override
    public void run() {
        for (AnimationStep step : steps) {
            step.run();
        }
    }
}

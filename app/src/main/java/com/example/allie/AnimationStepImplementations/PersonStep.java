package com.example.allie.AnimationStepImplementations;

import com.example.allie.AnimationStepImplementations.AnimationStep;
import com.example.allie.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonStep extends AnimationStep  {
    Person person;
    List<int[]> steps;

    public PersonStep (Person person) {
        this.person = person;
        steps = new ArrayList<>();
    }

    public void addMove (int... move) {
        steps.add(move);
    }

    @Override
    public void run() {
        person.setWalking(false);
        person.setHeight(400);
        for (int[] step : steps) {
            person.move(step[0], step[1]);
        }
    }
}


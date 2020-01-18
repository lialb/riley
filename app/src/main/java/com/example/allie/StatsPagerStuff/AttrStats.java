package com.example.allie.StatsPagerStuff;

import java.util.List;

public abstract class AttrStats<T> {
    String name;
    List<T> dataPoints;
    abstract T getAverage();
    void addDataPoint(T newPt) {
        dataPoints.add(newPt);
    }
    String getName() {
        return name;
    }
}

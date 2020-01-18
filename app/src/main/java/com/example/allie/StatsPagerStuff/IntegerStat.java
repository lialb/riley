package com.example.allie.StatsPagerStuff;


import java.util.List;

public class IntegerStat extends AttrStats<Integer> {
    public IntegerStat(String name, List<Integer> dataPoints) {
        this.name = name;
        this.dataPoints = dataPoints;
    }

    public Integer getAverage() {
        return this.dataPoints.get(0);
    }

}

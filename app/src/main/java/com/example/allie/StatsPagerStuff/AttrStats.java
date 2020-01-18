package com.example.allie.StatsPagerStuff;

import android.content.Context;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;

import java.util.ArrayList;
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

    abstract View addCharts(Context context, View root);
}
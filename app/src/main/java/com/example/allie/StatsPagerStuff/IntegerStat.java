package com.example.allie.StatsPagerStuff;


import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IntegerStat extends AttrStats<Integer> {
    public IntegerStat(String name, List<Integer> dataPoints) {
        this.name = name;
        this.dataPoints = dataPoints;
    }

    @Override
    public Integer getAverage() {
        return (int) dataPoints.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }


}

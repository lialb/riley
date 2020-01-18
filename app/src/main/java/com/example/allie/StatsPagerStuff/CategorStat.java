package com.example.allie.StatsPagerStuff;


import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class CategorStat extends AttrStats<String> {
    public CategorStat(String name, List<String> dataPoints) {
        this.name = name;
        this.dataPoints = dataPoints;
    }

    @Override
    public String getAverage() {
        //return (int) dataPoints.stream().mapToInt(Integer::intValue).average().getAsDouble();
        return "";
    }

}

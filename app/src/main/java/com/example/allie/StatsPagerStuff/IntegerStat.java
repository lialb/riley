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

public class IntegerStat extends AttrStats<Integer> {
    public IntegerStat(String name, List<Integer> dataPoints) {
        this.name = name;
        this.dataPoints = dataPoints;
    }

    @Override
    public Integer getAverage() {
        return (int) dataPoints.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }

    @Override
    public View addCharts(Context context, View root) {
        PieChart chart = new PieChart(context);
        chart.setMinimumHeight(500);
        ((LinearLayout) root).addView(chart);
        List<PieEntry> entries = new ArrayList<PieEntry>();
        for (Integer i: dataPoints) {
            entries.add(new PieEntry(i));
        }
        PieDataSet dataSet = new PieDataSet(entries, this.name);
        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.invalidate();
        return root;
    }

}

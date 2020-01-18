package com.example.allie.StatsPagerStuff;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;

import com.example.allie.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AttrStats<T> {
    String name;
    List<T> dataPoints;
    public PieChart chart;


    abstract T getAverage();

    void addDataPoint(T newPt) {
        dataPoints.add(newPt);
    }

    String getName() {
        return name;
    }

    public abstract View addPieChart(Context context, View root);
    /*View addPieChart(Context context, View root) {
        PieChart chart = new PieChart(context);
        chart.setMinimumHeight(900);
        ((LinearLayout) root).addView(chart);
        List<PieEntry> entries = new ArrayList<PieEntry>();


        Map<T, Integer> histo = makeHistogram();

        List<T> keyList = new ArrayList<>(histo.keySet());
        keyList = Collections.sort(keyList);
        for (T key : Collections.sort( keyList) ) {
            entries.add(new PieEntry(histo.get(key), key.toString()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "");

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(context.getColor(R.color.darkMediumGray));
        colors.add(context.getColor(R.color.spanishPink));
        colors.add(context.getColor(R.color.powderBlue));
        colors.add(context.getColor(R.color.cambridgeBlue));
        colors.add(context.getColor(R.color.defaultBackground));

        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter(chart));
        pieData.setValueTextColor(R.color.colorAccent);
        pieData.setValueTextSize(0);
        chart.setData(pieData);

        chart.getDescription().setEnabled(false);
        //show percentages or actual counts
        chart.setUsePercentValues(true);

        chart.setHighlightPerTapEnabled(true);
        chart.setDrawHoleEnabled(true);
        chart.setMinAngleForSlices(30);

        chart.setHoleColor(context.getColor(R.color.cardview_dark_background));

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        chart.setEntryLabelColor(Color.BLUE);
        chart.setCenterTextColor(Color.RED);
        chart.setEntryLabelTextSize(12);
        chart.setEntryLabelTypeface(Typeface.MONOSPACE);

        chart.setDrawEntryLabels(false);

        chart.invalidate();
        return root;
    }*/

    View addHistogramChart(Context context, View root) {
        BarChart chart = new BarChart(context);
        chart.setMinimumHeight(600);
        ((LinearLayout) root).addView(chart);
        List<BarEntry> entries = new ArrayList<BarEntry>();


        Map<T, Integer> histo = makeHistogram();

        for (T key : histo.keySet()) {
            entries.add( new BarEntry(  Integer.parseInt(key.toString()), Integer.parseInt(histo.get(key).toString()) ));
        }
        BarDataSet dataSet = new BarDataSet(entries, "");

        ArrayList<Integer> colors = new ArrayList<>();

       // colors.add(context.getColor(R.color.darkMediumGray));
        colors.add(context.getColor(R.color.spanishPink));
       // colors.add(context.getColor(R.color.powderBlue));
        colors.add(context.getColor(R.color.cambridgeBlue));
        //colors.add(context.getColor(R.color.defaultBackground));

        colors.add(context.getColor(R.color.primaryRed));
        colors.add(context.getColor(R.color.primaryDarkBlue));
        colors.add(context.getColor(R.color.primaryDarkTurq));
        colors.add(context.getColor(R.color.primaryGray));

        dataSet.setColors(colors);

        BarData barData = new BarData(dataSet);
        //barData.setValueFormatter(new For(chart));
        barData.setValueTextColor(R.color.colorAccent);
        barData.setValueTextSize(12);
        barData.setDrawValues(false);
        chart.setData(barData);

        chart.getDescription().setEnabled(false);
        //show percentages or actual counts
        chart.setHighlightPerTapEnabled(true);
        chart.setDrawGridBackground(true);
        chart.setDrawValueAboveBar(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxis.setAxisMinimum(0);
        yAxis.setTextSize(10);
        chart.getAxisRight().setEnabled(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setEnabled(false);


        chart.invalidate();
        return root;
    }

    public abstract Map<T, Integer> makeHistogram() ;

}
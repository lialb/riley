package com.example.allie.StatsPagerStuff;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;

import com.example.allie.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategStat extends AttrStats<String> {
    public CategStat(String name, List<String> dataPoints) {
        this.name = name;
        this.dataPoints = dataPoints;
    }

    @Override
    public String getAverage() {
        long ls = this.dataPoints.stream().filter(line -> !"Male".equals(line)).count();
        long ms = this.dataPoints.stream().filter(line -> "Male".equals(line)).count();
        if (ms > ls) return "Male " + (ms/(ls+ms)) + "%" ;
        else return "Female " + ((double)ls/(ls+ms))*100 + "%";
    }

    public Map<String, Integer> makeHistogram() {

        Map<String, Integer> histo = new HashMap<>();
        for (String point : dataPoints) {

            histo.put(point, histo.getOrDefault(point, 0) + 1);
        }


        return histo;
    }

    @Override
    public View addPieChart(Context context, View root) {
        chart = new PieChart(context);
        chart.setMinimumHeight(900);
        ((LinearLayout) root).addView(chart);
        List<PieEntry> entries = new ArrayList<PieEntry>();


        Map<String, Integer> histo = makeHistogram();

        List<String> keyList = new ArrayList<>(histo.keySet());
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size() ; i++ ) {
            entries.add(new PieEntry(histo.get(keyList.get(i)), keyList.get(i).toString()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "");

        ArrayList<Integer> colors = new ArrayList<>();

        //colors.add(context.getColor(R.color.darkMediumGray));
        //colors.add(context.getColor(R.color.spanishPink));
        colors.add(context.getColor(R.color.spanishPink));

        colors.add(context.getColor(R.color.primaryDarkBlue));

        colors.add(context.getColor(R.color.primaryLightTurq));

        //colors.add(context.getColor(R.color.cambridgeBlue));
        //colors.add(context.getColor(R.color.defaultBackground));
        colors.add(context.getColor(R.color.primaryDarkTurq));
        colors.add(context.getColor(R.color.powderBlue));

        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter(chart));
        pieData.setValueTextColor(R.color.powderBlue);
        //pieData.setValueTextSize(12);
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
        l.setTextSize(14);
        l.setDrawInside(false);
        l.setYOffset(-20);
        //fix age spacing , more left over chart, move text
        //move key right, add space between number and dash

        chart.setEntryLabelColor(Color.BLUE);
        chart.setCenterTextColor(Color.RED);
        chart.setEntryLabelTextSize(12);
        chart.setEntryLabelTypeface(Typeface.MONOSPACE);

        chart.setDrawEntryLabels(false);

        chart.invalidate();
        return root;
    }



}

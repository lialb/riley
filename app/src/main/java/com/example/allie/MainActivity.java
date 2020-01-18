package com.example.allie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.allie.StatsPagerStuff.AttrStats;
import com.example.allie.StatsPagerStuff.IntegerStat;
import com.example.allie.StatsPagerStuff.StatsAdaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    StatsAdaptor adaptor;
    List<AttrStats> stats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout root = (RelativeLayout) findViewById(R.id.root);

        stats = new ArrayList<>();
        stats.add(new IntegerStat("Age", Arrays.asList(70, 70, 45, 30, 43)));
        stats.add(new IntegerStat("Income", Arrays.asList(70000, 70000, 45000, 30000, 43000)));
        stats.add(new IntegerStat("Money Spent", Arrays.asList(170, 200, 45, 130, 30)));
        adaptor = new StatsAdaptor(stats, this);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adaptor);


    }
}


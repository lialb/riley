package com.example.allie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.allie.AnimationStepImplementations.AnimationStep;
import com.example.allie.AnimationStepImplementations.BackgroundChange;
import com.example.allie.AnimationStepImplementations.VisualizeAnimation;
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
    private CustomCanvasView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas = findViewById(R.id.custom_canvas_view);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Test button
            }
        });

        // Transition animation to be triggered by pager
        List<AnimationStep> animations = new ArrayList<>();
        Person person = canvas.getPerson();
        Background bg = canvas.getBackgroundDrawable();
        StackDrawable income = new StackDrawable(10, 60, R.drawable.money_stack, this);
        canvas.addDrawable(income);

        animations.add(new BackgroundChange(getResources().getColor(R.color.spanishPink), person, bg));
        animations.add(new VisualizeAnimation(income, person, bg));
//        animations.add(new BackgroundChange(getResources().getColor(R.color.cambridgeBlue), person, bg));
        animations.add(new BackgroundChange(getResources().getColor(R.color.powderBlue), person, bg));

        stats = new ArrayList<>();
        stats.add(new IntegerStat("Age", Arrays.asList(70, 70, 45, 30, 43)));
        stats.add(new IntegerStat("Income", Arrays.asList(70000, 70000, 45000, 30000, 43000)));
        stats.add(new IntegerStat("Money Spent", Arrays.asList(170, 200, 45, 130, 30)));
        adaptor = new StatsAdaptor(stats, this);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adaptor);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                animations.get(position).run();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}


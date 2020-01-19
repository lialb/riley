package com.example.allie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.allie.AnimationStepImplementations.AnimationStep;
import com.example.allie.AnimationStepImplementations.BackgroundStep;
import com.example.allie.AnimationStepImplementations.ComboStep;
import com.example.allie.AnimationStepImplementations.GraphicStep;
import com.example.allie.StatsPagerStuff.AttrStats;
import com.example.allie.StatsPagerStuff.IntegerStat;
import com.example.allie.StatsPagerStuff.StatsAdaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

        // Transition animation to be triggered by pager
        final List<AnimationStep> animations = new ArrayList<>();
        Person person = canvas.getPerson();
        Background bg = canvas.getBackgroundDrawable();
        MoneyStackGraphic incomeGraphic = new MoneyStackGraphic(10, this);
        BusinessStackGraphic businessStackGraphic = new BusinessStackGraphic(4, this);
        canvas.addDrawable(incomeGraphic);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Test button
                person.start();
            }
        });

        animations.add(new BackgroundStep(getResources().getColor(R.color.cambridgeBlue), person, bg));

        AnimationStep[] incomeStep = {
            new GraphicStep(incomeGraphic, person, bg, canvas),
            new BackgroundStep(getResources().getColor(R.color.spanishPink), person, bg)
        };


        AnimationStep[] buisnessSizeStep = {
                new GraphicStep(businessStackGraphic, person, bg, canvas),
                new BackgroundStep(getResources().getColor(R.color.powderBlue), person, bg)
        };

        animations.add(new ComboStep(incomeStep, person, bg));
        animations.add(new ComboStep(buisnessSizeStep, person, bg));

        stats = new ArrayList<>();

        Random rand = new Random();
        ArrayList<Integer> age = new ArrayList();

        for (int i = 0; i < 1000;  i++) {
            int new_age = (int) ( rand.nextGaussian() * 30 + 45);
            new_age = Math.max(5,new_age);
            new_age = Math.min(100,new_age);
            age.add(new_age);
        }
        ArrayList income = new ArrayList();
        for (int i = 0; i < 1000;  i++) {
            income.add(((int)(Math.random() * 100000)));
        }
        age.sort(Integer::compareTo);
        stats.add(new IntegerStat("Age - ", age));
        stats.add(new IntegerStat("Income - ", income));
        stats.add(new IntegerStat("Money Spent - ", Arrays.asList(170, 200, 45, 130, 30)));
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


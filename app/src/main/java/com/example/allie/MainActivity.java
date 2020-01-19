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
import com.example.allie.StatsPagerStuff.CategStat;
import com.example.allie.StatsPagerStuff.IntegerStat;
import com.example.allie.StatsPagerStuff.SpendingGraphic;
import com.example.allie.StatsPagerStuff.StatsAdaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    StatsAdaptor adaptor;
    private CustomCanvasView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas = findViewById(R.id.custom_canvas_view);
        List<AttrStats> stats = getStats();

        // Transition animation to be triggered by pager
        final List<AnimationStep> animations = new ArrayList<>();
        Person person = canvas.getPerson();
        Background bg = canvas.getBackgroundDrawable();
        MoneyStackGraphic incomeGraphic = new MoneyStackGraphic(10, this);
        BusinessStackGraphic businessStackGraphic = new BusinessStackGraphic(4, this);
        SpendingGraphic spendingGraphic = new SpendingGraphic(2, this);
        String genderString = stats.get(3).getAverage().toString();
        genderString = genderString.substring(genderString.length()-5, genderString.length()-2);
        GenderAnimation genderAnimation = new GenderAnimation((int) Double.parseDouble(genderString), this);


        canvas.addDrawable(spendingGraphic);
        spendingGraphic.startAnimation();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Test button
            person.setWalking(true);
            person.teleport(-30, 1000);
            person.move(2000, 1200);
            }
        });

//        animations.add(new BackgroundStep(getResources().getColor(R.color.cambridgeBlue), person, bg));


        AnimationStep[] spendingStep = {
            new GraphicStep(spendingGraphic, person, bg, canvas),
            new BackgroundStep(this.getColor(R.color.cambridgeBlue), person, bg)
        };

        AnimationStep[] incomeStep = {
            new GraphicStep(incomeGraphic, person, bg, canvas),
            new BackgroundStep(this.getColor(R.color.spanishPink), person, bg)
        };


        AnimationStep[] buisnessSizeStep = {
                new GraphicStep(businessStackGraphic, person, bg, canvas),
                new BackgroundStep(this.getColor(R.color.powderBlue), person, bg)
        };

        AnimationStep[] genderStep = {
                new GraphicStep(genderAnimation, person, bg, canvas),
                new BackgroundStep(this.getColor(R.color.darkMediumGray), person, bg)
        };

        animations.add(new ComboStep(spendingStep, person, bg));
        animations.add(new ComboStep(incomeStep, person, bg));
        animations.add(new ComboStep(buisnessSizeStep, person, bg));
        animations.add(new ComboStep(genderStep, person, bg));

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
    private List<AttrStats> getStats() {
        List<AttrStats> stats = new ArrayList<>();

        Random rand = new Random();
        rand.setSeed(1);
        ArrayList<Integer> age = new ArrayList();

        for (int i = 0; i < 1000;  i++) {
            int new_age = (int) ( rand.nextGaussian() * 30 + 45);
            new_age = Math.max(5,new_age);
            new_age = Math.min(100,new_age);
            age.add(new_age);
        }
        ArrayList<Integer> income = new ArrayList();
        for (int i = 0; i < 1000;  i++) {
            int new_income = (int) (rand.nextGaussian() * 15 + 65);
            new_income = Math.min(250, new_income);
            new_income = Math.max(15, new_income);
            income.add(new_income);
        }
        ArrayList<String> genders = new ArrayList<>();
        for (int i : income) {
            if ( i > 65) {
                genders.add("Male");
            } else {
                genders.add("Female");
            }
        }

        stats.add(new IntegerStat("Money Spent - ", Arrays.asList(170, 200, 45, 130, 30)));
        stats.add(new IntegerStat("Income - ", income));
        stats.add(new IntegerStat("Age - ", age));
        stats.add(new CategStat("Gender - ", genders ));
        return stats;
    }
}


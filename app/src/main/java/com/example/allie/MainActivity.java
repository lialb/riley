package com.example.allie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
                // canvas.getBackgroundDrawable().updateColor(getResources().getColor(R.color.spanishPink));
                Path path = new Path();
                path.moveTo(500, 800);
                path.lineTo(100, 800);
                canvas.getPerson().getArm().moveArm(path);
            }
        });
    }
}

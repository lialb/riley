package com.example.allie;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.LinearLayout;
import android.os.Bundle;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        CanvasTest canvas = new CanvasTest(this);
        linearLayout.addView(canvas);
    }
//    private Canvas mcanvas = new Canvas();
//    private Paint mpaint = new Paint();
//    private Bitmap
}

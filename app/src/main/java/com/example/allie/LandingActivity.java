package com.example.allie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LandingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ImageView port = (ImageView) findViewById(R.id.riley);
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.avataaars);
        RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), avatar);
        roundDrawable.setCircular(true);
        TextView t = (TextView) findViewById(R.id.intro);
        String next = "This is <font color='#1ec4b4'>Riley</font>, your average customer.";
        t.setText(Html.fromHtml( next));
        port.setImageDrawable(roundDrawable);
    }

    public void loadStats(View v) {
        Intent nIntent = new Intent(this, MainActivity.class);
        startActivity(nIntent);
    }
}

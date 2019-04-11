package com.curtesmalteser.drawoncanvas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.curtesmalteser.radarchart.RadarChart;

public class MainActivity extends AppCompatActivity {

   // RadarChart circles;
    ClockView clockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // circles = findViewById(R.id.myCircles);
        clockView = findViewById(R.id.clock);
    }
}

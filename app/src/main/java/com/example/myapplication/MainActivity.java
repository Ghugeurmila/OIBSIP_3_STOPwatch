package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    TextView start,pause,reset;
    boolean isRunning = false;
    private long pauseOffset;
   @SuppressLint("MissingInflatedId")
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer=findViewById(R.id.chronometer);
       chronometer.setFormat("Time: %s");
       chronometer.setBase(SystemClock.elapsedRealtime());
        start=findViewById(R.id.Start);
       pause=findViewById(R.id.Pause);
       reset=findViewById(R.id.Reset);

       chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
           @Override
           public void onChronometerTick(Chronometer chronometer) {
               if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) {
                   chronometer.setBase(SystemClock.elapsedRealtime());
                   Toast.makeText(MainActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
               }
           }
       });

       start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            if (!isRunning) {
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                chronometer.start();
                isRunning = true;
            }
           }
       });
       pause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (isRunning) {
                   chronometer.stop();
                   pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                   isRunning = false;
               }
           }
       });
       reset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               chronometer.setBase(SystemClock.elapsedRealtime());
               pauseOffset = 0;
           }
       });
    }
}
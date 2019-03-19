package com.example.attendence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import az.plainpie.PieView;

public class StatsActivity extends AppCompatActivity {
    String subjectName;
    int present, absent, off, total;
    double percent;
    PieView pieView;
    TextView t;
    double CONST = 0.75;
    public static final String MyPREFERENCES = "MyPrefs";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Intent i = getIntent();
        subjectName = i.getExtras().getString("subjectName");
        // Toast.makeText(getBaseContext(),subjectName,Toast.LENGTH_SHORT).show();
        SettingsActivity sa = new SettingsActivity();
        //-----------------------------------------
        CONST = getPercent() * 0.01;
        //Toast.makeText(getBaseContext(),"cal:"+CONST,Toast.LENGTH_SHORT).show();
        //-----------------------------------------
        calculatePercantage();
    }

    void calculatePercantage() {
        present = 0;
        absent = 0;
        off = 0;

        int status = 1;
        if (status == 1)
            present++;
        else if (status == 0)
            off++;
        else
            absent++;


        total = present + absent;
        percent = (present * 100.0) / total;
        percent = Math.round(percent * 100.0) / 100.0;//rounding to two decimal places
        pieView = (PieView) findViewById(R.id.pieView);
        pieView.setInnerTextVisibility(View.VISIBLE);
        pieView.setInnerText(Double.toString(percent));

    }
    public int getPercent() {
        SharedPreferences sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int PERCENT = sp.getInt("percent_key", 75);
        return PERCENT;

    }
}


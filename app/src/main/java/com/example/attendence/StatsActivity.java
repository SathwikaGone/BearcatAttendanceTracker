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

    }
    public int getPercent() {
        SharedPreferences sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int PERCENT = sp.getInt("percent_key", 75);
        return PERCENT;

    }
}


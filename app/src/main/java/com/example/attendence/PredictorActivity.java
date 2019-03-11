package com.example.attendence;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class PredictorActivity extends AppCompatActivity {
    mehdi.sakout.fancybuttons.FancyButton predictBtn;
    TextView current, predicted;

    ImageButton help;
    //---------------------------------------------------------
    final String msg = "Predict your percentage by " +
            "entering the number of classes" +
            " you will attend or miss!\n\n" +
            "Think for a while you are on a holiday and you won't be able to attend your classes.\n\n" +
            "Relax! Just input the number of classes you gonna miss and we'll predict your percentage!\n\n" +
            "Note:Choose your subject from the drop down list.";

    //-------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //------------------------------------------------
        help = (ImageButton) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(PredictorActivity.this);
                dialog.setTitle("How to use?");
                dialog.setMessage(msg);
                dialog.create().show();

            }
        });


    }
}


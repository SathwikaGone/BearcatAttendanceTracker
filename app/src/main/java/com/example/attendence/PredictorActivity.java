package com.example.attendence;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import mehdi.sakout.fancybuttons.FancyButton;


public class PredictorActivity extends AppCompatActivity {
    mehdi.sakout.fancybuttons.FancyButton predictBtn;
    TextView current,predicted;
    int present,absent,off,total,x,y;
    double percent,predictedpercent;
    Spinner spinner;
    String spinner_subject;
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

        spinner = (Spinner) findViewById(R.id.spinnerSub2);
        addItemsToSpinner();
        //---------------------------------------
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinner_subject = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //---------------------------------------
        predictBtn= (FancyButton) findViewById(R.id.predict);

        predictBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialNumberPicker present_count = (MaterialNumberPicker)findViewById(R.id.presentcnt);
                x=present_count.getValue();
                MaterialNumberPicker miss_count = (MaterialNumberPicker)findViewById(R.id.misscnt);
                y=miss_count.getValue();
                //Toast.makeText(getBaseContext(),String.valueOf(x),Toast.LENGTH_SHORT).show();

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
                    percent = Math.round(percent * 100.0) / 100.0;

                    predictedpercent=(present+x)*100.0/(total+x+y);
                    predictedpercent=Math.round(predictedpercent*100.0)/100.0;

                    current = (TextView) findViewById(R.id.current_text);
                    predicted = (TextView) findViewById(R.id.predicted_text);

                    current.setText("Current Percentage : " + percent + " %");
                    predicted.setText("Predicted Percentage : " + predictedpercent + " %");

            }
        });



    }

    public void addItemsToSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null)
            spinner.setAdapter(spinnerAdapter);

                String str = "Android";
                spinnerAdapter.add(str);
                spinnerAdapter.notifyDataSetChanged();
        }

}


package com.example.attendence;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;


public class TimeTableActivity extends AppCompatActivity {
    ImageButton help_table;
    com.cuboid.cuboidcirclebutton.CuboidButton btn,btn0,btn1,btn2,btn3,btn4,btn5,btn6;
    final String msg="Know your classes for a particular day by clicking on the appropriate button.\n";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);


        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        help_table= (ImageButton) findViewById(R.id.help_table);
        help_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(TimeTableActivity.this);
                dialog.setTitle("How to use?");
                dialog.setMessage(msg);
                dialog.create().show();

            }
        });
        ButtonBar();
        //--------------------------------

        //--------------------------------
    }
    void ButtonBar()
    {
        btn=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Mon);
        decolorAll();
        btn.setBackgroundColor(Color.rgb(156, 39, 176));


        btn0=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Mon);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decolorAll();
                btn0.setBackgroundColor(Color.rgb(156, 39, 176));

            }
        });

        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Tue);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decolorAll();
                btn1.setBackgroundColor(Color.rgb(156, 39, 176));

            }
        });

        btn2=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Wed);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decolorAll();
                btn2.setBackgroundColor(Color.rgb(156, 39, 176));

            }
        });

        btn3=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Thu);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decolorAll();
                btn3.setBackgroundColor(Color.rgb(156, 39, 176));

            }
        });

        btn4=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Fri);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decolorAll();
                btn4.setBackgroundColor(Color.rgb(156, 39, 176));

            }
        });

        btn5=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Sat);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decolorAll();
                btn5.setBackgroundColor(Color.rgb(156, 39, 176));

            }
        });

        btn6=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Sun);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decolorAll();
                btn6.setBackgroundColor(Color.rgb(156, 39, 176));

            }
        });

    }
    void decolorAll()
    {
        com.cuboid.cuboidcirclebutton.CuboidButton btn1;
        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Mon);
        btn1.setBackgroundColor(Color.TRANSPARENT);
        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Tue);
        btn1.setBackgroundColor(Color.TRANSPARENT);
        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Wed);
        btn1.setBackgroundColor(Color.TRANSPARENT);
        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Thu);
        btn1.setBackgroundColor(Color.TRANSPARENT);
        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Fri);
        btn1.setBackgroundColor(Color.TRANSPARENT);
        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Sat);
        btn1.setBackgroundColor(Color.TRANSPARENT);
        btn1=(com.cuboid.cuboidcirclebutton.CuboidButton)findViewById(R.id.Sun);
        btn1.setBackgroundColor(Color.TRANSPARENT);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
package com.example.attendence;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends FragmentActivity {

    CaldroidFragment caldroidFragment;
    final String[] items = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    com.github.clans.fab.FloatingActionButton help_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CreateCalendar();
        help_btn = (FloatingActionButton)findViewById(R.id.help);
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg="Use this calendar to mark your attendance. \n\n" +
                        "1) Use the drop down list to change the subject. \n\n" +
                        "2) Click on any date to see the classes for that day.\n\n" +
                        "3) Click on any event to mark your attendance.\n\n" +
                        "Note : \n\n" +
                        "GREEN : all classes attended.\n" +
                        "YELLOW : all classes were off.\n" +
                        "ORANGE : some classes attended.\n" +
                        "RED : all classes missed.\n";
                AlertDialog.Builder dialog = new AlertDialog.Builder(CalendarActivity.this);
                dialog.setTitle("How to use?");
                dialog.setMessage(msg);
                dialog.create().show();
            }
        });

    }

    void CreateCalendar() {
        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        caldroidFragment.setMaxDate(Calendar.getInstance().getTime());
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();
    }
}
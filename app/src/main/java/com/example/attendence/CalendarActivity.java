package com.example.attendence;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends FragmentActivity {

    CaldroidFragment caldroidFragment;
    final String[] items = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    Spinner spinner;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter adapter;
    ListView listView;
    String spinner_subject;
    View listv1,viewc;
    Date datec;
    List<Date> listdates=new ArrayList<Date>();
    String clickedDay;

    //~~~~~~TAGS~~~~~~~~~~~~~~~
    final int PRESENT=1;
    final int ABSENT=-1;
    final int OFF=0;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    com.github.clans.fab.FloatingActionButton help_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CreateCalendar();
        spinner = (Spinner) findViewById(R.id.spinnerSub);
        addItemsToSpinner();

        adapter = new ArrayAdapter<String>(this, R.layout.listview, arrayList);
        listView = (ListView) findViewById(R.id.calendarList);
        listView.setAdapter(adapter);

        help_btn = (FloatingActionButton) findViewById(R.id.help);
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Use this calendar to mark your attendance. \n\n" +
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


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinner_subject = spinner.getSelectedItem().toString();
                arrayList.clear();
                adapter.clear();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }


    private void showMenu(final View listv,final String listtime)
    {
        final String startlisttime=listtime.substring(0,listtime.indexOf('-')-1);
        final String endlisttime=listtime.substring(listtime.indexOf('-')+2);
        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        final String currentdate=formatter.format(datec);
       // Toast.makeText(CalendarActivity.this,clickedDay, Toast.LENGTH_SHORT).show();

       // Toast.makeText(getBaseContext(),startlisttime,Toast.LENGTH_SHORT).show();
       // Toast.makeText(getBaseContext(),endlisttime,Toast.LENGTH_SHORT).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_color);
        builder.setItems(R.array.menu_color, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {



               // Toast.makeText(getBaseContext(), Integer.toString(id), Toast.LENGTH_SHORT).show();
                if (which == 0)
                {

                    listv.setBackgroundResource(R.color.present);
                    listv1.refreshDrawableState();
                    caldroidFragment.setBackgroundDrawableForDate(new ColorDrawable(Color.rgb(76, 140, 80)), datec);
                }
                else if (which == 1){

                    listv.setBackgroundResource(R.color.absent);
                    listv1.refreshDrawableState();
                    caldroidFragment.setBackgroundDrawableForDate(new ColorDrawable(Color.rgb(229, 57, 53)), datec);
                }
                else if (which == 2)
                {

                    listv.setBackgroundResource(R.color.off);
                    listv1.refreshDrawableState();
                    caldroidFragment.setBackgroundDrawableForDate(new ColorDrawable(Color.rgb(255,215,0)), datec);
                }
                caldroidFragment.refreshView();
            }
        });
        builder.show();
    }

    public void addItemsToSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.add("Android");
        spinnerAdapter.notifyDataSetChanged();

    }

    void CreateCalendar()
    {
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
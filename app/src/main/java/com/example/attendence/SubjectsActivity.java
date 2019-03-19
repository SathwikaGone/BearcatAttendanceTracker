package com.example.attendence;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SubjectsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter adapter;
    com.github.clans.fab.FloatingActionButton addButton;
    EditText edittext;
    AlertDialog.Builder alert;
    SQLiteDatabase mydatabase;
    Cursor resultSet;
    ImageButton help;
    final String msg="Click on the course to view " +
            "your statistics for that course.\n\n" +
            "Long press on the course to edit or delete that course.\n\n";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        help = (ImageButton) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SubjectsActivity.this);
                dialog.setTitle("How to use?");
                dialog.setMessage(msg);
                dialog.create().show();

            }
        });
        MyRecyclerViewAdapter set = new MyRecyclerViewAdapter(null);
        set.setLayout(1);

        /*adapter = new ArrayAdapter<String>(this, R.layout.listview, list);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);*/
        //--------------------------------------------
        if (mAdapter != null)
            mAdapter = null;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        ArrayList results = new ArrayList<DataObject>();
        //--------------------------------------------
        int index = 0;
        DataObject obj = new DataObject("Android", "");
        results.add(index, obj);


        mAdapter = new MyRecyclerViewAdapter(results);
        mRecyclerView.setAdapter(mAdapter);
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
               /* Log.i(LOG_TAG, " Clicked on Item " + position);
                final String text = ((TextView)v.findViewById(R.id.textView)).getText().toString();
                Intent i = new Intent(getBaseContext(),StatsActivity.class);
                i.putExtra("subjectName",text);
                startActivity(i);*/
            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                mRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                final String text = ((TextView)view.findViewById(R.id.textView)).getText().toString();
                Intent i = new Intent(getBaseContext(),StatsActivity.class);
                i.putExtra("subjectName",text);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, final int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SubjectsActivity.this);
                dialog.setTitle("Options");
                final String sub = ((TextView)view.findViewById(R.id.textView)).getText().toString();
                String[] items = {"Edit","Delete"};
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0)
                        {
                            showCustomDialog(sub,position);
                        }

                        if (which == 1)      //Delete
                        {


                            SQLiteDatabase mydatabase = openOrCreateDatabase("SubjectsDB", MODE_PRIVATE, null);
                            mydatabase.execSQL("DELETE FROM subject WHERE subjects='"+sub+"';");
                            //Toast.makeText(getBaseContext(), sub + " Successfully Deleted ", Toast.LENGTH_SHORT).show();
                            ((MyRecyclerViewAdapter) mAdapter).deleteItem(position);
                        }
                    }
                });
                dialog.create().show();
            }

        }));

        //--------------------------------------------------
    }

    public void showCustomDialog(final String old1,final int pos)
    {
        final Dialog dialog2 = new Dialog(SubjectsActivity.this);
        dialog2.setContentView(R.layout.custom_dialog1);
        dialog2.setTitle("Enter course name:");

        Button btn = (Button) dialog2.findViewById(R.id.btn);
        // if button is clicked, close the custom dialog
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) dialog2.findViewById(R.id.editText);
                String changedName = editText.getText().toString();
                if(changedName.equals(""))
                {
                    Toast.makeText(getBaseContext(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ((MyRecyclerViewAdapter) mAdapter).deleteItem(pos);
                    DataObject obj=new DataObject(changedName,"");
                    ((MyRecyclerViewAdapter) mAdapter).addItem(obj,pos);
                }
                dialog2.dismiss();
            }
        });
        dialog2.show();
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

}
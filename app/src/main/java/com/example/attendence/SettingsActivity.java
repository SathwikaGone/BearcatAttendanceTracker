package com.example.attendence;




import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    TextView tv;
    Button setTime,setTime1;
    int PERCENT = 75;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tv= (TextView) findViewById(R.id.textView);
        tv.setText(Integer.toString(PERCENT));
        setTime= (Button) findViewById(R.id.reminder_time);
        setTime1= (Button) findViewById(R.id.notification_time);
        //-------------------------------------------------
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog1("reminder_key");
            }
        });
        //---------------------------------------------------restoring values

        setTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog1("notification_key");
            }
        });

//-------------------------------------------------------------

    }
    public void showChangeLangDialog1(final String str) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog2, null);
        dialogBuilder.setView(dialogView);

        final TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        dialogBuilder.setTitle("Select Time");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}

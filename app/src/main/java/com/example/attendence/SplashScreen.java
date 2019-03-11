package com.example.attendence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView suar= (ImageView) findViewById(R.id.logo);
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);

    }
}
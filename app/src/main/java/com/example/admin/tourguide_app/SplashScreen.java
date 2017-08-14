package com.example.admin.tourguide_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread mythread  = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent myIntent = new Intent(SplashScreen.this,FrontActivity.class);
                    startActivity(myIntent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        getSupportActionBar().hide();
        mythread.start();
    }
}

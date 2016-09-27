package com.cleancampus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.cleancampus.R;

/**
 * Created by Chanpreet on 26-09-2016.
 */
public class SplashActivity extends AppCompatActivity {

    private Thread splash;
    private int sleepTime=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        splash = new Thread() {
            public void run() {
                try {
                    sleep(sleepTime);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent mainIntent = new Intent("com.cleancampus.activity.login");
                    startActivity(mainIntent);
                    finish();
                }
            }
        };
        splash.start();
    }
}

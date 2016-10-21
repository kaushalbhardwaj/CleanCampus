package com.cleancampus.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.cleancampus.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;


public class SplashActivity extends AppCompatActivity {

    private Thread splash;
    private int sleepTime=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        setStatusBarColor("#33465D");
        final UserInfo u=SharedPreference.getSharedPreferInfo(SplashActivity.this);

        splash = new Thread() {
            public void run() {
                try {
                    sleep(sleepTime);
                } catch (Exception e) {
                    e.printStackTrace( );
                } finally {

                    try{
                    if(u.getEmailId().equals("")||u==null) {
                        Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    else {
                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();

                    }}
                    catch (Exception e)
                    {
                        Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(mainIntent);
                        finish();


                    }
                }
            }
        };
        splash.start();
    }

    private void setStatusBarColor(String color) {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setTintColor(Color.parseColor(color));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
}

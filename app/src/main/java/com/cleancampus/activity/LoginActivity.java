package com.cleancampus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cleancampus.R;

/**
 * Created by Chanpreet on 27-09-2016.
 */
public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login =(Button)findViewById(R.id.login_button);
        register=(Button) findViewById(R.id.signup);
        username =(EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(loginIntent);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}

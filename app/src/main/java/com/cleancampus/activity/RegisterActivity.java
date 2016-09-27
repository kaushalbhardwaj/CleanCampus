package com.cleancampus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cleancampus.R;

/**
 * Created by Chanpreet on 27-09-2016.
 */
public class RegisterActivity extends AppCompatActivity{
    private Button register;
    private EditText username;
    private EditText password;
    private EditText name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register =(Button) findViewById(R.id.register);
        username =(EditText) findViewById(R.id.register_username);
        password =(EditText) findViewById(R.id.register_password);
        name =(EditText) findViewById(R.id.name);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}

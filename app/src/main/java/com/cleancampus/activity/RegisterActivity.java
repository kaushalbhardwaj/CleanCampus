package com.cleancampus.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cleancampus.R;
import com.cleancampus.model.User;
import com.cleancampus.response.LoginResponse;
import com.cleancampus.response.RegisterResponse;
import com.cleancampus.rest.ApiClient;
import com.cleancampus.rest.ApiInterface;
import com.cleancampus.tools.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chanpreet on 27-09-2016.
 */
public class RegisterActivity extends AppCompatActivity{
    private Button register;
    private EditText email;
    private EditText password;
    private EditText name;
    ProgressDialog pd;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.c_layout)
    CoordinatorLayout clayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
        register =(Button) findViewById(R.id.register);
        email =(EditText) findViewById(R.id.register_username);
        password =(EditText) findViewById(R.id.register_password);
        name =(EditText) findViewById(R.id.name);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setPassword(password.getText().toString());
                user.setConfirmPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
                user.setFirstName(name.getText().toString());
                user.setLastName(name.getText().toString());
                user.setPhoneNumber(phone.getText().toString());
                pd = Tools.getProgressDialog(RegisterActivity.this);
                pd.show();
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<RegisterResponse> call = apiService.createUser(user);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse>call, Response<RegisterResponse> response) {
                        pd.dismiss();
                        Log.e("register",response.code()+" "+response.isSuccessful());
                        if(response.isSuccessful())
                        {
                            if(response.body().getMessage().equals("SignupSuccessful")) {
                                Log.e("register", response.body().getMessage());

                                Toast.makeText(RegisterActivity.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                                Intent registerIntent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(registerIntent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();


                            }


                        }
                        else
                        {
                            Log.e("login",response.toString());

                            Toast.makeText(RegisterActivity.this, "Error Try Again Later!!!", Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse>call, Throwable t) {
                        Log.e("activity_login", t.toString());
                        pd.dismiss();
                        Snackbar snackbar = Snackbar
                                .make(clayout, "Can't connect to Internet!!", Snackbar.LENGTH_LONG);

                        snackbar.show();
                    }
                });


            }
        });
    }
}

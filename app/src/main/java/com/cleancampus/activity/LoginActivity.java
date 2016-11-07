package com.cleancampus.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cleancampus.R;
import com.cleancampus.response.LoginResponse;
import com.cleancampus.rest.ApiClient;
import com.cleancampus.rest.ApiInterface;
import com.cleancampus.tools.Tools;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chanpreet on 27-09-2016.
 */
public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView registerText;
    private EditText email;
    private EditText password;
    ProgressDialog pd;
    @BindView(R.id.c_layout)
    CoordinatorLayout clayout;
    @BindView(R.id.input_username)
    TextInputLayout inputLayoutName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login =(Button)findViewById(R.id.login_button);
        registerText =(TextView) findViewById(R.id.register_text);
        email =(EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() || !isValidEmail(email.getText().toString())) {
                    inputLayoutName.setError("Wrong email");
                    requestFocus(email);
                    return;
                }
                pd = Tools.getProgressDialog(LoginActivity.this);
                pd.show();
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<LoginResponse> call = apiService.signin(email.getText().toString(),password.getText().toString());
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse>call, Response<LoginResponse> response) {
                        pd.dismiss();
                        if(response.isSuccessful())
                        {
                            Log.e("login",response.body().getToken());
                            UserInfo u=new UserInfo();
                            String a[]=email.getText().toString().split("@");
                            u.setEmailId(email.getText().toString());
                            u.setUserName(a[0]);
                            u.setPhoneNum("+91-9760492235");
                            SharedPreference.putSharedPreferInfo(getApplicationContext(),u);
                            Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(loginIntent);
                            finish();


                        }
                        else
                        {
                            Log.e("login",response.toString());

                            Snackbar snackbar = Snackbar
                                    .make(clayout, "Wrong Username or Password!!", Snackbar.LENGTH_LONG);

                            snackbar.show();

                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse>call, Throwable t) {
                        Log.e("activity_login", t.toString());
                        pd.dismiss();
                        Snackbar snackbar = Snackbar
                                .make(clayout, "Can't connect to Internet!!", Snackbar.LENGTH_LONG);

                        snackbar.show();
                    }
                });


            }
        });

       registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}

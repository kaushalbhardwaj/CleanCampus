package com.cleancampus.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleancampus.R;
import com.cleancampus.activity.LoginActivity;
import com.cleancampus.activity.MainActivity;
import com.cleancampus.activity.MapActivity;
import com.cleancampus.activity.SharedPreference;
import com.cleancampus.activity.UserInfo;
import com.cleancampus.adapter.Data;
import com.cleancampus.request.ProfileRequest;
import com.cleancampus.response.ComplaintResponse;
import com.cleancampus.response.ProfileResponse;
import com.cleancampus.rest.ApiClient;
import com.cleancampus.rest.ApiInterface;
import com.cleancampus.tools.Tools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView signout;
    AppCompatImageView log;
    TextView userName,email,ph;
    FloatingActionButton fabDust;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=(View)inflater.inflate(R.layout.fragment_profile, container, false);
        signout=(TextView)v.findViewById(R.id.signoutt);
        log=(AppCompatImageView)v.findViewById(R.id.logout);
        userName=(TextView)v.findViewById(R.id.usernameprofile);
        ph=(TextView)v.findViewById(R.id.phonen);
        email=(TextView)v.findViewById(R.id.textView2);
        fabDust=(FloatingActionButton)v.findViewById(R.id.fabDust);
        UserInfo u= SharedPreference.getSharedPreferInfo(getActivity().getApplicationContext());
        getProfile();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo u= new UserInfo();
                u.setEmailId("");
                u.setUserName("");
                u.setPhoneNum("");
                SharedPreference.putSharedPreferInfo(getActivity().getApplicationContext(),u);
                Intent i=new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo u= new UserInfo();
                u.setEmailId("");
                u.setUserName("");
                SharedPreference.putSharedPreferInfo(getActivity().getApplicationContext(),u);
                Intent i=new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        fabDust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Dialog dialog=new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.layout_dustbin_map);
                dialog.show();*/
                Intent mapIntent = new Intent(getActivity().getApplicationContext(), MapActivity.class);
                startActivity(mapIntent);
            }
        });

        return v;
    }

    private void getProfile() {
        final UserInfo ui= SharedPreference.getSharedPreferInfo(getActivity().getApplicationContext());
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        ProfileRequest pr=new ProfileRequest();
        pr.setEmail(ui.getEmailId());
        Call<ProfileResponse> call = apiService.getProfile(pr);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse>call, Response<ProfileResponse> response) {
                Log.e("profile",response.code()+" "+response.isSuccessful()+response.body().getName());
                if(response.isSuccessful())
                {
                    UserInfo ui2=new UserInfo();
                    ui2.setEmailId(ui.getEmailId());
                    ui2.setPhoneNum(response.body().getPhoneNumber());
                    String a[]=response.body().getName().split("    ");
                    ui2.setUserName(a[0]);
                    updateView(ui2);
                }
                else
                {
                    UserInfo ui2=new UserInfo();
                    ui2.setEmailId(ui.getEmailId());
                    ui2.setPhoneNum(ui.getPhoneNum());
                    ui2.setUserName(ui.getUserName());
                    updateView(ui2);
                    Log.e("profile",response.toString());

                }

            }

            @Override
            public void onFailure(Call<ProfileResponse>call, Throwable t) {
                UserInfo ui2=new UserInfo();
                ui2.setEmailId(ui.getEmailId());
                ui2.setPhoneNum(ui.getPhoneNum());
                ui2.setUserName(ui.getUserName());
                updateView(ui2);
                Log.e("profile", t.toString());
            }
        });
    }

    private void updateView(UserInfo u) {
        userName.setText(u.getUserName());
        email.setText(u.getEmailId());
        ph.setText(u.getPhoneNum());

    }

}

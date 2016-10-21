package com.cleancampus.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleancampus.R;
import com.cleancampus.activity.LoginActivity;
import com.cleancampus.activity.SharedPreference;
import com.cleancampus.activity.UserInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    private TextView signout;
    AppCompatImageView log;
    TextView userName,email;
    FloatingActionButton fabDust;


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        signout=(TextView)v.findViewById(R.id.signoutt);
        log=(AppCompatImageView)v.findViewById(R.id.logout);
        userName=(TextView)v.findViewById(R.id.usernameprofile);
        email=(TextView)v.findViewById(R.id.textView2);
        fabDust=(FloatingActionButton)v.findViewById(R.id.fabDust);
        UserInfo u= SharedPreference.getSharedPreferInfo(getActivity().getApplicationContext());
        userName.setText(u.getUserName());
        email.setText(u.getEmailId());

        signout.setOnClickListener(new View.OnClickListener() {
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
                Dialog dialog=new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.layout_dustbin_map);
                dialog.show();
            }
        });

        return v;
    }

}

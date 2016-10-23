package com.cleancampus.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;



public class SharedPreference {

    public static final String MyPREFERENCES = "UserInfo1" ;
    public static SharedPreferences sharedpreferences;
    public static boolean putSharedPreferInfo(Context con,UserInfo u)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("nameKey", u.getUserName());
        editor.putString("emailKey", u.getEmailId());
        editor.putString("phoneKey",u.getPhoneNum());
        editor.commit();
        return true;

    }
    public static UserInfo getSharedPreferInfo(Context con)
    {
        UserInfo user1=new UserInfo();

        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        user1.setUserName(sharedpreferences.getString("nameKey",null));
        user1.setEmailId(sharedpreferences.getString("emailKey", null));
        user1.setPhoneNum(sharedpreferences.getString("phoneKey", null));
        return user1;
    }
}

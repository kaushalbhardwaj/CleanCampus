package com.cleancampus.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khome on 20/10/16.
 */

public class LoginBody {
    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

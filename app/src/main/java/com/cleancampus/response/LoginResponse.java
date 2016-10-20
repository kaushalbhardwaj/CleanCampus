package com.cleancampus.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khome on 20/10/16.
 */

public class LoginResponse {
    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

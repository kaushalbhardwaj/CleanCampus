package com.cleancampus.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khome on 23/10/16.
 */

public class ProfileRequest {
    @SerializedName("email")
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

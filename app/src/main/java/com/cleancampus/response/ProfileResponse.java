package com.cleancampus.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khome on 23/10/16.
 */

public class ProfileResponse {
    @SerializedName("name")
    String name;
    @SerializedName("phone_number")
    String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

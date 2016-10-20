package com.cleancampus.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khome on 19/10/16.
 */

public class ComplaintResponse {
    @SerializedName("msg")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

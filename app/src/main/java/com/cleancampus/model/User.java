package com.cleancampus.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khome on 19/10/16.
 */

public class User {
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("password")
    String password;
    @SerializedName("confirm_password")
    String confirmPassword;
    @SerializedName("phone_number")
    String phoneNumber;
    @SerializedName("email")
    String email;
    public User()
    {


    }
    public User(String fn,String password,String email,String phoneNumber)
    {
        this.firstName=fn;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;


    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.cleancampus.adapter;

/**
 * Created by Chanpreet on 28-09-2016.
 */
public class Data {

    private int id ;
    private String username;
    private String location;
    private String email;
    private String title;
    private String description;
    private String date;
    private int status;



   public  Data()
    {
        id =0;

        username="";
        location="";
        email="";
        title="";
        description="";
        date ="";
        status =0;
    }
    public Data(String username1, String location1, String email1,String title1, String description1, int status1, String date1)
    {
        username = username1;
        location = location1;
        email = email1;
        title = title1;
        description =description1;
        date = date1;
        status = status1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String locate) {
        this.location = locate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

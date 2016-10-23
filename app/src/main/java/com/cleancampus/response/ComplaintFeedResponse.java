package com.cleancampus.response;

import com.cleancampus.model.Complaint;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by khome on 23/10/16.
 */

public class ComplaintFeedResponse {
    @SerializedName("complaints")
    List<Complaint> complaints;

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
}

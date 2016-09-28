package com.cleancampus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleancampus.R;
import com.cleancampus.adapter.ComplaintAdapater;

/**
 * A simple {@link Fragment} subclass.
 */
public class Complaint extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View complaintView= inflater.inflate(R.layout.fragment_complaint, container, false);
        recyclerView = (RecyclerView)complaintView.findViewById(R.id.complaint_recycler);
        adapter = new ComplaintAdapater(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return complaintView;
    }

}

package com.cleancampus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleancampus.R;
import com.cleancampus.adapter.FeedAdapter;
import com.cleancampus.model.Complaint;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feed extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v=inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyler_feed);
        adapter= new FeedAdapter(getActivity().getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return v;
    }

}

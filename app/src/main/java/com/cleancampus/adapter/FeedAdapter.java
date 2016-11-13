package com.cleancampus.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancampus.R;
import com.cleancampus.model.Complaint;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by chanpreet on 7/11/16.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Complaint> list = new ArrayList<>();

    public FeedAdapter(Context context1, ArrayList<Complaint> list1) {
        context = context1;
        list = list1;
        Collections.reverse(list1);
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout_feed,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    protected  class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView username;
        private TextView title;

        ViewHolder(View view)
        {
            super(view);
            username =(TextView)view.findViewById(R.id.feed_username);
            title =(TextView)view.findViewById(R.id.feed_title);
        }
    }
}

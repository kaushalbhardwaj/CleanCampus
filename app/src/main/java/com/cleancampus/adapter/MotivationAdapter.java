package com.cleancampus.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chanpreet on 27-09-2016.
 */
public class MotivationAdapter extends RecyclerView.Adapter {


    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
    protected class ViewHolder extends RecyclerView.ViewHolder
    {

        ViewHolder(View view)
        {
            super(view);
        }
    }
}

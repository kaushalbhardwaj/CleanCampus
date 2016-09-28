package com.cleancampus.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleancampus.R;
import com.cleancampus.fragment.Complaint;

/**
 * Created by Chanpreet on 27-09-2016.
 */
public class ComplaintAdapater extends RecyclerView.Adapter {

    private Context context;

   public  ComplaintAdapater(Context context1)
    {
        context = context1;
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout_compliant,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
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

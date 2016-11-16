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
    private int image[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h};
    private String imageText[];

    public FeedAdapter(Context context1) {
        context = context1;
        imageText = context.getResources().getStringArray(R.array.text);
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout_feed,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(imageText[position]);
        holder.image.setImageResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return imageText.length;
    }

    protected  class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView text;
        private ImageView image;

        ViewHolder(View view)
        {
            super(view);
            text =(TextView)view.findViewById(R.id.feed_text);
            image = (ImageView)view.findViewById(R.id.feed_image);
        }
    }
}

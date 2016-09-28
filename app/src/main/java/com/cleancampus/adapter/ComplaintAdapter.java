package com.cleancampus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancampus.R;

/**
 * Created by Chanpreet on 28-09-2016.
 */
public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    private Context context;

    public ComplaintAdapter(Context context1)
    {
        context = context1;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_complain,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    protected  class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView username;
        private TextView description;
        private ImageView userimage;
        private ImageView like;
        private ImageView bookmark;
        private ImageView share;
        private ImageView status;

        ViewHolder(View view)
        {
            super(view);
            username =(TextView)view.findViewById(R.id.username);
            description =(TextView)view.findViewById(R.id.description);
            userimage =(ImageView) view.findViewById(R.id.userimage);
            like =(ImageView) view.findViewById(R.id.like);
            bookmark =(ImageView) view.findViewById(R.id.bookmark);
            share =(ImageView) view.findViewById(R.id.share);
            status =(ImageView) view.findViewById(R.id.status);
        }
    }
}

package com.cleancampus.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancampus.R;
import com.cleancampus.activity.ComplaintDetail;

import java.util.ArrayList;
import java.util.Collections;


public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Data> list = new ArrayList<>();
     private int pos =0;
    public ComplaintAdapter(Context context1, ArrayList<Data> list1)
    {
        context = context1;
        list = list1;
        Collections.reverse(list1);
    }
    @Override
    public int getItemCount() {
        return(null != list?list.size():0);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_complain,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        pos = position;
        Log.e(list.get(position).getUsername(),"p:"+position);
        holder.username.setText(list.get(position).getUsername());
        holder.description.setText(list.get(position).getDescription());
        holder.title.setText(list.get(position).getTitle());
        int c=position%4;
        switch (c)
        {
            case 0:
                holder.userimage.setImageResource(R.drawable.img1);
                break;
            case 1:
                holder.userimage.setImageResource(R.drawable.img2);
                break;
            case 2:
                holder.userimage.setImageResource(R.drawable.img3);
                break;
            case 3:
                holder.userimage.setImageResource(R.drawable.img4);
                break;
            default:
                holder.userimage.setImageResource(R.drawable.img1);

        }
        //holder.like.setImageResource(R.drawable.ic_favorite_black);
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

        /*holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView img1=(ImageView)view;


            }
        });

        holder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, list.get(position).getDescription());
                //shareIntent.putExtra(Intent.EXTRA_STREAM, context.uriToImage);
                shareIntent.setType("image/jpeg");
                context.startActivity(Intent.createChooser(shareIntent, "Share Complaint With"));

            }
        });

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complaintIntent = new Intent(context, ComplaintDetail.class);
                String title = list.get(position).getTitle();
                String des= list.get(position).getDescription();
                String name = list.get(position).getUsername();
                complaintIntent.putExtra("title",title);
                complaintIntent.putExtra("description",des);
                complaintIntent.putExtra("name",name);
                context.startActivity(complaintIntent);
            }
        });



    }
    public void notifyData(ArrayList<Data> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.list = myList;
        notifyDataSetChanged();
    }

    protected  class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView username;
        private TextView description;
        private TextView title;
        private ImageView userimage;
        private ImageView like;
        private ImageView bookmark;
        private ImageView share;
        private ImageView status;
        private CardView card;

        ViewHolder(View view)
        {
            super(view);

            card=(CardView)view.findViewById(R.id.card);
            username =(TextView)view.findViewById(R.id.username);
            description =(TextView)view.findViewById(R.id.description);
            title =(TextView)view.findViewById(R.id.title);
            userimage =(ImageView) view.findViewById(R.id.userimage);
            /*like =(ImageView) view.findViewById(R.id.like);
            bookmark =(ImageView) view.findViewById(R.id.bookmark);*/
            share =(ImageView) view.findViewById(R.id.share);
            status =(ImageView) view.findViewById(R.id.status);
        }
    }
}

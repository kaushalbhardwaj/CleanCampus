package com.cleancampus.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cleancampus.R;

/**
 * Created by chanpreet on 23/10/16.
 */

public class TipsPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int tipsImage[]={R.drawable.spalsh,R.drawable.spalsh,R.drawable.spalsh,R.drawable.spalsh};
    private String tipsText[]={"Cleanliness may be defined to be the emblem of purity of mind. -Joseph Addison","Cleanliness may be defined to be the emblem of purity of mind. -Joseph Addison","Cleanliness may be defined to be the emblem of purity of mind. -Joseph Addison","Cleanliness may be defined to be the emblem of purity of mind. -Joseph Addison"};
    public TipsPagerAdapter(Context context1)
    {
        context = context1;
    }
    @Override
    public int getCount() {
        return tipsImage.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tips_rowlayout,container,false);
        ImageView image = (ImageView)view.findViewById(R.id.tips_image);
        TextView text = (TextView)view.findViewById(R.id.tips_text);
        image.setImageResource(tipsImage[position]);
        text.setText(tipsText[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}

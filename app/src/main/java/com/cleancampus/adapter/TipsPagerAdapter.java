package com.cleancampus.adapter;

import android.content.Context;
import android.graphics.Color;
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
    private int tipsImage[]={R.drawable.tips2,R.drawable.tips3,R.drawable.tips4,R.drawable.tips1};
    private String tipsText[]={"If you see trash off the path, don't hesitate – go and grab it","Go over a tree guide with your parents and find trees that will grow to an appropriate height in your climate zone","Be sure to rinse and clean reusable shopping bags about once a week, to keep them from getting grimy",
                               "Organize the recycling. Check bottles, jars, and cans to be sure they're reasonably clean" };
    private String tipsHead[]={"Clean Up","Plantation","Reuse Items","Use Dustbin"};
    private String headColor[]={"#4FA759","#7D6924","#B67992","#63714D"};
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
        TextView textHead = (TextView)view.findViewById(R.id.tips_head);
        image.setBackgroundResource(tipsImage[position]);
        text.setText(tipsText[position]);
        textHead.setText(tipsHead[position]);
        textHead.setTextColor(Color.parseColor(headColor[position]));
        container.addView(view);
        return view;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.85f;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}

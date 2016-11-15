package com.cleancampus.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chanpreet on 27-09-2016.
 */
public class MainAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    HashMap<Integer,Fragment> mPageReferenceMap=new HashMap<Integer, Fragment>();


    public MainAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment, String title)
    {
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment myFragment = fragments.get(position);
        mPageReferenceMap.put(position, myFragment);
        return fragments.get(position);
    }
    public Fragment getFragment(int key) {
        return mPageReferenceMap.get(key);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

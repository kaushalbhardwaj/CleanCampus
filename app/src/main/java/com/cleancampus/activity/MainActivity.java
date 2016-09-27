package com.cleancampus.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.cleancampus.R;
import com.cleancampus.adapter.MainAdapter;
import com.cleancampus.fragment.Complaint;
import com.cleancampus.fragment.Feed;
import com.cleancampus.fragment.Motivation;
import com.cleancampus.fragment.Profile;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager =(ViewPager) findViewById(R.id.viewpager);

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFragment(new Complaint(), "Complaint");
        mainAdapter.addFragment(new Feed(), "Feed");
        mainAdapter.addFragment(new Motivation(), "Motivation");
        mainAdapter.addFragment(new Profile(),"Profile");

        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

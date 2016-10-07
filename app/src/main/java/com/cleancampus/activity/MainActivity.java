package com.cleancampus.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
    private TextView tabTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabTitle = (TextView) findViewById(R.id.tab_title);
        //toolbar =(Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager =(ViewPager) findViewById(R.id.viewpager);

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("CleanCampus");
        //toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFragment(new Complaint(),"");
        mainAdapter.addFragment(new Feed(),"");
        mainAdapter.addFragment(new Motivation(), "");
        mainAdapter.addFragment(new Profile(),"");

        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_elemental_tip);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_chat2);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_person_white_24px);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                switch(tab.getPosition())
                {
                    case 0:
                        tabTitle.setText("Complaint");
                        break;
                    case 1:
                        tabTitle.setText("Tips");
                        break;
                    case 2:
                        tabTitle.setText("Activity Feed");
                        break;
                    case 3:
                        tabTitle.setText("Profile");
                        break;
                    default:
                        tabTitle.setText("none");
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#9AA2AE"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}

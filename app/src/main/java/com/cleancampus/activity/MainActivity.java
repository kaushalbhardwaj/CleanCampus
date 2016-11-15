package com.cleancampus.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.cleancampus.R;
import com.cleancampus.adapter.MainAdapter;
import com.cleancampus.fragment.ComplaintFragment;
import com.cleancampus.fragment.Feed;
import com.cleancampus.fragment.TipsFragment;
import com.cleancampus.fragment.ProfileFragment;
import com.cleancampus.model.Complaint;
import com.cleancampus.request.ProfileRequest;
import com.cleancampus.response.ProfileResponse;
import com.cleancampus.rest.ApiClient;
import com.cleancampus.rest.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @BindView(R.id.c_layout)
    CoordinatorLayout c_layout;
    Complaint com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager =(ViewPager) findViewById(R.id.viewpager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CleanCampus");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFragment(new ComplaintFragment(),"");
        mainAdapter.addFragment(new Feed(),"");
        mainAdapter.addFragment(new TipsFragment(), "");
        mainAdapter.addFragment(new ProfileFragment(),"");

        viewPager.setOffscreenPageLimit(4);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1000) {
                com=new Complaint();
                com.setEmail(data.getStringExtra("email"));
                com.setDescription(data.getStringExtra("description"));
                com.setTitle(data.getStringExtra("title"));
                com.setLatitude(data.getStringExtra("latitude"));
                com.setLongitude(data.getStringExtra("longitude"));
                com.setStatus(data.getStringExtra("status"));
                com.setImage(data.getStringExtra("image"));
                Log.e("data","got it");
            int index = viewPager.getCurrentItem();
            MainAdapter adapter = ((MainAdapter) viewPager.getAdapter());
            Fragment fragment = adapter.getFragment(index);
            ComplaintFragment cf=(ComplaintFragment)fragment;
            cf.addComplaint(com);


        }
    }



}

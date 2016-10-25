package com.cleancampus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cleancampus.R;
import com.cleancampus.adapter.TipsPagerAdapter;
import com.cleancampus.model.Complaint;
import com.cleancampus.response.ComplaintResponse;
import com.cleancampus.model.LoginBody;
import com.cleancampus.response.LoginResponse;
import com.cleancampus.rest.ApiClient;
import com.cleancampus.rest.ApiInterface;

import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipsFragment extends Fragment {


    ViewPager vp;
    TipsPagerAdapter adapter;

    public TipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_tips, container, false);
        vp=(ViewPager)v.findViewById(R.id.pager_tips);
       CircleIndicator circleIndicator = (CircleIndicator) v.findViewById(R.id.indicator);
        adapter = new TipsPagerAdapter(getActivity().getApplicationContext());
        vp.setClipToPadding(false);
        vp.setPageMargin(35);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(adapter);
        circleIndicator.setViewPager(vp);
        return v;

    }

    }


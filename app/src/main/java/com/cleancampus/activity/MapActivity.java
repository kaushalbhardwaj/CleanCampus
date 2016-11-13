package com.cleancampus.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cleancampus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by chanpreet on 11/11/16.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private SupportMapFragment mapFragment;
    private GoogleMap mgoogleMap;
    private double latitude[]={26.936804,26.936737,26.936061,26.936090,26.936740,26.936556,26.935870,26.934997,26.934658,26.934736, 26.934375,26.934349,26.933856,26.933237,26.933275,26.933098,26.932202,26.936771};
    private double longitude[]={75.924486,75.924572,75.925205,75.924829,75.923298,75.923043,75.923413,75.924333,75.923397,75.923056,75.923212,75.924239,75.923164,75.923182,75.921721,75.922276,75.922665,75.922914};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap=googleMap;
        for(int i = 0 ; i < latitude.length ; i++ ) {

            createMarker(latitude[i], longitude[i]);
        }
    }

    private void createMarker(double v, double v1) {
        LatLng marker = new LatLng(v,v1);
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 17));
        mgoogleMap.addMarker(new MarkerOptions().title("Use Me").position(marker));
    }

}

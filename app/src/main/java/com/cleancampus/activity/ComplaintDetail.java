package com.cleancampus.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cleancampus.R;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;

/**
 * Created by chanpreet on 18/10/16.
 */

public class ComplaintDetail extends AppCompatActivity implements OnMapReadyCallback {

    private Toolbar toolbar;
    private Bundle bundle;
    private String title;
    private String description;
    private String name;
    private TextView title1;
    private TextView desc;
    private TextView username;
    private Button bt;
    private SupportMapFragment mapFragment;
    protected static final String TAG = "MainActivity";
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private String latitude;
    private String longitude;
    private String image;
    //LocationRequest mLocationRequest;
    //LatLng latLng;
    //GoogleMap mGoogleMap;
    //Marker currLocationMarker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Fresco.initialize(ComplaintDetail.this);
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString("title");
            description = bundle.getString("description");
            name = bundle.getString("name");
            latitude = bundle.getString("latitude");
            longitude = bundle.getString("longitude");
            image = bundle.getString("image");
            Log.e("image",image);
        }
        setContentView(R.layout.complaint_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Complaint");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);


        title1 = (TextView) findViewById(R.id.complaint_title);
        desc = (TextView) findViewById(R.id.complaint_description);
        username = (TextView) findViewById(R.id.complaint_username);
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.sdvImage);
        title1.setText(title);
        desc.setText(description);
        username.setText(name);
        Uri uri = Uri.parse(image);
        /*
        draweeView.setImageURI(uri);*/
        int width = 50, height = 50;
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
        .setResizeOptions(new ResizeOptions(width, height)).build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request).setOldController(draweeView.getController()).build();
        draweeView.setController(controller);

    }

    /*protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION
            },10);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            mGoogleMap.addMarker(new MarkerOptions().title("Location:").position(latLng));
            tv.setText(mLastLocation.getLatitude()+"\n"+mLastLocation.getLongitude());
        } else {
            Toast.makeText(this,"Nothing detected", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }
    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }*/



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mGoogleMap = googleMap;
       // mGoogleMap.setMyLocationEnabled(true);
        //buildGoogleApiClient();
        //mGoogleApiClient.connect();
        //LatLng marker = new LatLng(-33.867,151.206);
        LatLng marker = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 17));
        googleMap.addMarker(new MarkerOptions().title("Location:").position(marker));
    }


}

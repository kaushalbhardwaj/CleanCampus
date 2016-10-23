package com.cleancampus.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cleancampus.Manifest;
import com.cleancampus.R;
import com.cleancampus.activity.LoginActivity;
import com.cleancampus.activity.RegisterActivity;
import com.cleancampus.activity.SharedPreference;
import com.cleancampus.activity.UserInfo;
import com.cleancampus.adapter.ComplaintAdapter;
import com.cleancampus.adapter.Data;
import com.cleancampus.adapter.Dbhelper;
import com.cleancampus.model.Complaint;
import com.cleancampus.response.ComplaintResponse;
import com.cleancampus.response.RegisterResponse;
import com.cleancampus.rest.ApiClient;
import com.cleancampus.rest.ApiInterface;
import com.cleancampus.tools.Tools;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Data> list = new ArrayList<>();
    private FloatingActionButton fab;
    private TextView title;
    private TextView description;
    CoordinatorLayout cd;
    ProgressDialog pd;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    LatLng latLng;
    protected static final String TAG = "Complaint";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View complaintView = inflater.inflate(R.layout.fragment_complaint, container, false);

        final Dbhelper dbhelper = new Dbhelper(getActivity().getApplicationContext());

        list = dbhelper.getData();

        recyclerView = (RecyclerView) complaintView.findViewById(R.id.recyler_complaint);
        title = (TextView) complaintView.findViewById(R.id.complaint);
        description = (TextView) complaintView.findViewById(R.id.description);
        cd=(CoordinatorLayout) getActivity().findViewById(R.id.c_layout);


        adapter = new ComplaintAdapter(getContext(), list);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        final UserInfo u= SharedPreference.getSharedPreferInfo(getActivity().getApplicationContext());
        fab = (FloatingActionButton) complaintView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.dialog, null)).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;
                        EditText complaint = (EditText) f.findViewById(R.id.complaint);
                        EditText description = (EditText) f.findViewById(R.id.description);

                        Complaint complaint1=new Complaint();
                        UserInfo ui=SharedPreference.getSharedPreferInfo(getActivity().getApplicationContext());
                        complaint1.setEmail(ui.getUserName());
                        complaint1.setDescription(description.getText().toString());
                        complaint1.setTitle(complaint.getText().toString());
                        final Complaint com=complaint1;
                        dialog.dismiss();
                        pd = Tools.getProgressDialog(getActivity());
                        pd.show();
                        ApiInterface apiService =
                                ApiClient.getClient().create(ApiInterface.class);
                        Call<ComplaintResponse> call = apiService.sendComplaint(complaint1);
                        call.enqueue(new Callback<ComplaintResponse>() {
                            @Override
                            public void onResponse(Call<ComplaintResponse>call, Response<ComplaintResponse> response) {
                                pd.dismiss();
                                Log.e("complain",response.code()+" "+response.isSuccessful()+response.body().getMessage());
                                if(response.isSuccessful())
                                {

                                    Snackbar snackbar = Snackbar
                                            .make(cd, "Complain Added Successfully!!!", Snackbar.LENGTH_LONG);

                                    snackbar.show();
                                    Data data = new Data(u.getUserName(), "", u.getEmailId(), com.getTitle(), com.getDescription(), 0, "");
                                    dbhelper.add(data);
                                    list.add(0, data);
                                    adapter.notifyItemInserted(0);
                                    recyclerView.scrollToPosition(0);

                                }
                                else
                                {
                                    Snackbar snackbar = Snackbar
                                            .make(cd, "Error Try Again Later!!!", Snackbar.LENGTH_LONG);

                                    snackbar.show();
                                    Log.e("complain",response.toString());

                                }

                            }

                            @Override
                            public void onFailure(Call<ComplaintResponse>call, Throwable t) {
                                Log.e("complain", t.toString());
                                pd.dismiss();
                                Snackbar snackbar = Snackbar
                                        .make(cd, "Can't connect to Internet!!", Snackbar.LENGTH_LONG);

                                snackbar.show();
                            }
                        });

                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).setNeutralButton("Location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buildGoogleApiClient();

                        //Toast.makeText(getActivity().getApplicationContext(),mLastLocation.getLatitude()+" "+mLastLocation.getLongitude(),Toast.LENGTH_LONG).show();
                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return complaintView;
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onStop() {
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

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( new String[]{
                android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION
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
            //Toast.makeText(getActivity().getApplicationContext(),mLastLocation.getLatitude()+" "+ mLastLocation.getLongitude(),Toast.LENGTH_LONG).show();
            Log.v("Coordinates",mLastLocation.getLatitude()+" "+mLastLocation.getLongitude());
        } else {
            //Toast.makeText(getActivity().getApplicationContext(),"Nothing detected", Toast.LENGTH_LONG).show();
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
    }

}

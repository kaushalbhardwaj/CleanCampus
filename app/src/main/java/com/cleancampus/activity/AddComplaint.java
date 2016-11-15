package com.cleancampus.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cleancampus.R;
import com.cleancampus.adapter.Dbhelper;
import com.cleancampus.model.Complaint;
import com.cleancampus.response.ComplaintResponse;
import com.cleancampus.rest.ApiClient;
import com.cleancampus.rest.ApiInterface;
import com.cleancampus.tools.Tools;
import com.cleancampus.tools.ipaulpro.afilechooser.utils.FileUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chanpreet on 13/11/16.
 */

public class AddComplaint extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Toolbar toolbar;
    private EditText complaint;
    private EditText description;
    private Button takePic;
    private Button submit;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    LatLng latLng;
    protected static final String TAG = "Complaint";
    Dbhelper dbhelper;
    public static String name;
    ProgressDialog pd;
    UserInfo u;
    Complaint complaint1;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    ImageView imageGarbage;
    private Uri fileUri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcomplaint);
        toolbar = (Toolbar) findViewById(R.id.complaint_toolbar);
        complaint = (EditText) findViewById(R.id.complaint);
        description = (EditText) findViewById(R.id.description);
        submit = (Button) findViewById(R.id.submit);
        imageGarbage=(ImageView)findViewById(R.id.complaintimage);
        toolbar.setTitle("Add Complaint");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaint1 = new Complaint();
                UserInfo ui = SharedPreference.getSharedPreferInfo(getApplicationContext());
                complaint1.setEmail(ui.getEmailId());
                complaint1.setDescription(description.getText().toString());
                complaint1.setTitle(complaint.getText().toString());
                complaint1.setLatitude("  ");
                complaint1.setLongitude("  ");
                complaint1.setStatus("2");
                pd = Tools.getProgressDialog(AddComplaint.this);
                pd.show();
                buildGoogleApiClient();
            }
        });
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    private void captureImage() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(AddComplaint.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            requestPermissions( new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            },10);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "CleanCampus");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        );
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
            name="IMG_" + timeStamp + ".jpg";

        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }


        return mediaFile;
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AddComplaint.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            complaint1.setLatitude(mLastLocation.getLatitude()+"");
            complaint1.setLongitude(mLastLocation.getLongitude()+"");
            sendToServer();
        } else {
            Toast.makeText(AddComplaint.this,"Error Could not fetch location!!!",Toast.LENGTH_LONG).show();
        }
    }

    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }
    private void sendToServer() {

        int c=0;
        final Complaint com=complaint1;

        RequestBody email1 = createPartFromString(com.getEmail());
        RequestBody title1 = createPartFromString(com.getTitle());
        RequestBody description1 = createPartFromString(com.getDescription());
        RequestBody latitude1 = createPartFromString(com.getLatitude());
        RequestBody longitude1 = createPartFromString(com.getLongitude());
        RequestBody status1 = createPartFromString(com.getStatus());

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("email", email1);
        map.put("title", title1);
        map.put("description", description1);
        map.put("latitude", latitude1);
        map.put("longitude", longitude1);
        map.put("status", status1);


        if(fileUri!=null)
        {
            final File file = FileUtils.getFile(this, fileUri);


        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        dbhelper = new Dbhelper(getApplicationContext());
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<ComplaintResponse> call = apiService.sendComplaint(map, body);
            call.enqueue(new Callback<ComplaintResponse>() {
                @Override
                public void onResponse(Call<ComplaintResponse> call, Response<ComplaintResponse> response) {
                    pd.dismiss();
                    Log.e("complain", response.code() + " " + response.isSuccessful() + response.body().getMessage());
                    if (response.isSuccessful()) {


                        Toast.makeText(AddComplaint.this, "Complaint Registered Succesfully!!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent();
                        intent.putExtra("email", com.getEmail());
                        intent.putExtra("title", com.getTitle());
                        intent.putExtra("description", com.getDescription());
                        intent.putExtra("latitude", com.getLatitude());
                        intent.putExtra("longitude", com.getLongitude());
                        intent.putExtra("status", com.getStatus());
                        intent.putExtra("image", "http://cleancampus.herokuapp.com/api/media/images/"+name);
                        setResult(1000, intent);
                        finish();//finishing activity
                    /*Data data = new Data(u.getUserName(), "", u.getEmailId(), com.getTitle(), com.getDescription(), 0, "");
                    dbhelper.add(data);
                    */


                    } else {
                        Toast.makeText(AddComplaint.this, "Error Try Again Later!!!", Toast.LENGTH_LONG).show();
                        Log.e("complain", response.toString());

                    }

                }

                @Override
                public void onFailure(Call<ComplaintResponse> call, Throwable t) {
                    Log.e("complain", t.toString());
                    pd.dismiss();
                    Toast.makeText(AddComplaint.this, "Can't connect to Internet!!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            Toast.makeText(AddComplaint.this, "Please Take a Picture!!!!", Toast.LENGTH_SHORT).show();
            pd.dismiss();


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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            imageGarbage.setVisibility(View.VISIBLE);
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float scaleWidth = ((float) 300) / width;
            float scaleHeight = ((float) 300) / height;
            Matrix matrix = new Matrix();
            // RESIZE THE BIT MAP
            matrix.postScale(scaleWidth, scaleHeight);

            // "RECREATE" THE NEW BITMAP
            Bitmap resizedBitmap = Bitmap.createBitmap(
                    bitmap,0,0, width, height,matrix, false);
            imageGarbage.setImageResource(0);
            imageGarbage.setImageBitmap(resizedBitmap);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // successfully captured the image
                // launching upload activity
                previewMedia(true);

            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }



}

package com.cleancampus.tools;

import android.app.ProgressDialog;
import android.content.Context;

import com.cleancampus.activity.LoginActivity;

/**
 * Created by khome on 20/10/16.
 */

public class Tools {
    public static ProgressDialog getProgressDialog(Context context)
    {
        ProgressDialog pd=new ProgressDialog(context);
        pd.setTitle("Loading");
        pd.setMessage("Please Wait....");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return pd;

    }
}

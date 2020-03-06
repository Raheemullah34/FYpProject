package com.example.fypproject.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternatStatus {

    Context context;

    public InternatStatus(Context context) {
        this.context = context;
    }
    /*check network Access*/
    public boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}

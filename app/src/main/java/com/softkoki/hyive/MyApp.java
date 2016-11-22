package com.softkoki.hyive;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.softkoki.hyive.pojo.Entry;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gulkush on 8/14/2016.
 */
public class MyApp extends MultiDexApplication {

    public static Typeface tf_flipside, tf_awesome;
    public static Entry currentEntry;
    public static List<Entry> entries = new ArrayList<>();


    public void onCreate() {
        super.onCreate();
        tf_flipside = Typeface.createFromAsset(getAssets(), "fonts/flipside.ttf");
        tf_awesome = Typeface.createFromAsset(getAssets(), "fonts/awesome.ttf");


    }



    public boolean checkAskPermission(Activity activity, String permission, int request_code){
        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                permission) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{permission},
                    request_code);
            return false;

        }else{
            return true;
        }
    }




}

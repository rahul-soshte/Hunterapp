package com.example.hunter.planmap123.common;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hunter.planmap123.logger.Log;
import com.example.hunter.planmap123.logger.LogWrapper;


public class SampleFragmentBase extends FragmentActivity {
    /**
     * Base launcher activity to handle most of the common plumbing for samples
     */

    public static final String TAG="SampleActivityBase";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    super.onCreate(savedInstanceState);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        initializeLogging();

    }

    /**
     * Set up targets to receive log data
     */
    public void initializeLogging()
    {//Using log,front end to the logging chain ,emulates android.util.Log method signatures
        //Wraps Android'native og framework
        LogWrapper logWrapper=new LogWrapper();
        Log.setLogNode(logWrapper);
        Log.i(TAG,"Ready");


    }

}

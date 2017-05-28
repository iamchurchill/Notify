package com.samsoft.notify.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by FRIEDRICH M. SAM on 30-Dec-16.
 */

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras != null){
            String con = extras.getString(BatteryManager.EXTRA_PLUGGED);
            Toast.makeText(context, "CONNECTED", Toast.LENGTH_LONG);
        }
    }
}
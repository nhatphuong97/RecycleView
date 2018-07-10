package com.example.nhatphuong.boastcastrecieve;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class NetworkState extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Network is turned ON/OF", Toast.LENGTH_SHORT).show();
        String state= intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(TelephonyManager.EXTRA_STATE_RINGING.equals(state)){
            Toast.makeText(context, "A calling from" +intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_SHORT).show();
        }
    }
}

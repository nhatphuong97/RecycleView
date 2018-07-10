package com.example.nhatphuong.boastcastrecieve;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent();
//        intent.setAction("android.net.conn.CONNECTIVITY_CHANGE");
//        sendBroadcast(intent);
        NetworkState receiver = new NetworkState();
        final IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
      int Batery = intent.getIntExtra("level",0);
      if(Batery<10){
          Toast.makeText(context, "Low Batery" +Batery, Toast.LENGTH_SHORT).show();
      }else{
          Toast.makeText(context, "this Device Batery is "+Batery, Toast.LENGTH_SHORT).show();
      }
        }
    };
}

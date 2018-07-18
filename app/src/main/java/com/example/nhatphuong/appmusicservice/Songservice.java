package com.example.nhatphuong.appmusicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.io.IOException;

public class Songservice extends Service {
    private final IBinder songbinder = new SongBider();
    String data =null;
    Intent intent;
   MediaPlayer mPlayer ;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onbind", Toast.LENGTH_SHORT).show();
        return songbinder;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Toast.makeText(this, "on create", Toast.LENGTH_SHORT).show();
        //data=(String) intent.getExtras().get("idasd");
    }
    public class SongBider extends Binder {
    Songservice GetService(){
        return Songservice.this;
    }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "on desstroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "On Un bind ", Toast.LENGTH_SHORT).show();
        mPlayer.stop();
        mPlayer.release();
        return false;
        //return super.onUnbind(intent);
    }

    public void onPlay(String parth){
//        if(data!=null){
//            data = intent.getStringExtra("idasd");
//            System.out.println(data);
//        }
          //    data = intent.getStringExtra("idasd");
                try {
                        mPlayer= new MediaPlayer();
                       // mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mPlayer.setDataSource(parth);
                    mPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mPlayer.start();
    }
}

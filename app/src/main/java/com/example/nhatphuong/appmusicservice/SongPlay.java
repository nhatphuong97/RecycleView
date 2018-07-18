package com.example.nhatphuong.appmusicservice;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.io.IOException;

public class SongPlay extends AppCompatActivity {
   Intent playIntent;
    MediaPlayer mPlayer;
    Songservice songSrv;
    boolean musicBound;
    ImageButton mButtonPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);
        Intent mIntent = getIntent();
        Bundle mbundle= mIntent.getExtras();
        final String mString = mIntent.getStringExtra("data");
        System.out.println("cc" + mString);
//        mPlayer = new  MediaPlayer();
//        try {
//           // mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mPlayer.setDataSource(mString);
//            mPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mPlayer.start();
        mButtonPlay = findViewById(R.id.button_play);

        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               songSrv.onPlay(mString);
            }
        });


    }
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Songservice.SongBider binder = (Songservice.SongBider)service;
            //get SongBider
            songSrv = binder.GetService();
            //pass list
         //   songSrv.setList(songList);
           // musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
          //  musicBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        playIntent = new Intent(this, Songservice.class);
        bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        startService(playIntent);
    }
}

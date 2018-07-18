package com.example.nhatphuong.appmusicservice;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    public ArrayList<song> ArrayListSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayListSong = new ArrayList<>();
        mRecyclerView = findViewById(R.id.Recycler);
        //  System.out.println(ArrayListSong);

        getSongList();
        SongAdater mSongAdapter = new SongAdater( this,ArrayListSong);
        //System.out.println(mSongAdapter.getItemCount());
         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
     mRecyclerView.setAdapter(mSongAdapter);
    }

    public void getSongList() {
        ContentResolver musicContent = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicContent.query(musicUri, null, null, null, null);

        if (musicCursor != null && musicCursor.moveToFirst()) {
           // System.out.println("asdas đá ");
            int ParthColum =
                    musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int titleColum = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistColum =
                    musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
            do {
                String thisParth = musicCursor.getString(ParthColum);
                // System.out.println("cc" + thisId);
                String thisTitle = musicCursor.getString(titleColum);
                String thisArtist = musicCursor.getString(artistColum);
                ArrayListSong.add(new song(thisParth, thisTitle, thisArtist));

            } while (musicCursor.moveToNext());
//            System.out.println(ArrayListSong.get(4).getId());
//            System.out.println(ArrayListSong.get(5).getId());
//            System.out.println(ArrayListSong.get(6).getId());
        }

        //return ArrayListSong;
    }

//    public void Binddata(ArrayList<song> Arraylist) {
//        SongAdater mSongAdapter = new SongAdater(Arraylist, this);
//         mRecyclerView = findViewById(R.id.Recycler);
//        mRecyclerView.setAdapter(mSongAdapter);
//           System.out.println(Arraylist);
//
//    }
    //        private void checkPermissionContact() {
    //            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
    //                    == PackageManager.PERMISSION_DENIED) {
    //                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    //                    requestPermissions(new String[] { Manifest.permission.READ_CONTACTS },
    //                            1);
    //                }
    //
    //            } else {
    //                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    //                    requestPermissions(new String[] { Manifest.permission.READ_CONTACTS },
    //                            1);
    //                }
    //            }
    //        }
}

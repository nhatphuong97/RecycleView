package com.example.nhatphuong.appmusicservice;

import android.os.Parcelable;
import java.io.Serializable;

public class song  implements Serializable {

    private String id;
    private String title;
    private String artist;

    public song(String songID, String songTitle, String songArtist){
        id=songID;
        title=songTitle;
        artist=songArtist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

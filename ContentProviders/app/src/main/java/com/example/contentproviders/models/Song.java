package com.example.contentproviders.models;

public class Song {
    private String mId;
    private String mSongName;
    private String mSongAlbum;
    private String mSongArtist;
    private String mAlbumId;

    public Song(String id, String songName, String songAlbum, String songArtist, String albumId) {
        this.mId = id;
        this.mSongName = songName;
        this.mSongAlbum = songAlbum;
        this.mSongArtist = songArtist;
        this.mAlbumId = albumId;
    }

    public String getId() {
        return mId;
    }

    public String getSongName() {
        return mSongName;
    }

    public String getSongAlbum() {
        return mSongAlbum;
    }

    public String getSongArtist() {
        return mSongArtist;
    }

    public String getAlbumId() {
        return mAlbumId;
    }
}

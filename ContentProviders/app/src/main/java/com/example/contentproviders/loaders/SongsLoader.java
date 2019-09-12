package com.example.contentproviders.loaders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.contentproviders.models.Song;

import java.util.ArrayList;

public class SongsLoader {

    private static Uri mUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    private static String[] mProjection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID
    };

    private static SongsLoader mSongsLoader;

    public static synchronized SongsLoader getInstance() {
        if (mSongsLoader == null) {
            mSongsLoader = new SongsLoader();
        }
        return mSongsLoader;
    }

    public ArrayList<Song> getAllAudioFiles(Context context) {
        ArrayList<Song> songArrayList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                mUri,
                mProjection,
                null,
                null,
                "title ASC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                songArrayList.add(new Song(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            }
            cursor.close();
        }
        return songArrayList;
    }
}

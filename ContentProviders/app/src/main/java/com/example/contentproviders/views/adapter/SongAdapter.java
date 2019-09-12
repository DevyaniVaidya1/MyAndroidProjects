package com.example.contentproviders.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contentproviders.R;
import com.example.contentproviders.models.Song;
import com.example.contentproviders.views.customview.UbuntuTextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private ArrayList<Song> mSongsList;
    private Context mContext;

    public SongAdapter(ArrayList<Song> songsList, Context context) {
        this.mSongsList = songsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = mSongsList.get(position);



    }

    @Override
    public int getItemCount() {
        return mSongsList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {

        CircleImageView songThumb;
        UbuntuTextView songTitle;
        TextView songArtist;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);

            songThumb = itemView.findViewById(R.id.iv_song_thumb);
            songTitle = itemView.findViewById(R.id.tv_song_name);
            songArtist = itemView.findViewById(R.id.tv_song_artist);

        }
    }
}

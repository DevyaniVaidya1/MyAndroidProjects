package com.example.slidingtabsusingviewpager.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.slidingtabsusingviewpager.R;
import com.example.slidingtabsusingviewpager.model.MarvelMovie;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<MarvelMovie> mMovieList;
    private Context mContext;
    private Dialog mMyDialog;


    public RecyclerViewAdapter(List<MarvelMovie> response, Context context) {
        mMovieList = response;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        mMyDialog = new Dialog(mContext);
        mMyDialog.setContentView(R.layout.dialog_movie);
        mMyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialogCode(viewHolder);
                Snackbar.make(view, "test click" + String.valueOf(viewHolder.getAdapterPosition())
                        , Snackbar.LENGTH_LONG).show();
                mMyDialog.show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        MarvelMovie movie = mMovieList.get(position);
        holder.textView.setText(movie.getName());
        Glide.with(mContext).load(movie.getImageurl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView textView;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.profile_image);
            this.textView = itemView.findViewById(R.id.tv_title);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    /**
     * contain dialog code
     *
     * @param viewHolder which hold view
     */
    private void dialogCode(ViewHolder viewHolder) {
        //initialized dialog

        TextView movieName = mMyDialog.findViewById(R.id.tv_dialog_moviename);
        TextView team = mMyDialog.findViewById(R.id.tv_dialog_team);
        CircleImageView movieProfile = mMyDialog.findViewById(R.id.iv_dialog_movie_profile);
        TextView bio = mMyDialog.findViewById(R.id.tv_dialog_bio);
        //get value from list
        movieName.setText(mMovieList.get(viewHolder.getAdapterPosition()).getName());
        team.setText(mMovieList.get(viewHolder.getAdapterPosition()).getTeam());
        bio.setText(mMovieList.get(viewHolder.getAdapterPosition()).getCreatedby());

        Glide.with(mContext)
                .load(mMovieList.get(viewHolder.getAdapterPosition()).getImageurl())
                .into(movieProfile);
    }
}

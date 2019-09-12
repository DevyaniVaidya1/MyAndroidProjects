package com.example.jsonnewsapplication.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jsonnewsapplication.R;
import com.example.jsonnewsapplication.model.Articles;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<Articles> newsData;
    private String url;


    public NewsAdapter(Context context, List<Articles> newsData) {
        this.context = context;
        this.newsData = newsData;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_user_layout, parent,
                false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        Articles articles = newsData.get(position);
        holder.newsTitle.setText(articles.getTitle());
        url = articles.getUrl();
        Glide.with(holder.newsImage.getContext()).load(articles.getUrlToImage()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearlayout);
            newsImage = itemView.findViewById(R.id.iv_item_user_layout_userimage);
            newsTitle = itemView.findViewById(R.id.tv_layout_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        context.startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No application can handle this request."
                                + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}

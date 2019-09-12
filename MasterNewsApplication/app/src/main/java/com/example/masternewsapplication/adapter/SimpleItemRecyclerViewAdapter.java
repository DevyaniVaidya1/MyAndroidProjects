package com.example.masternewsapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.masternewsapplication.R;
import com.example.masternewsapplication.model.Articles;
import com.example.masternewsapplication.views.activity.ItemDetailActivity;
import com.example.masternewsapplication.views.activity.ItemListActivity;
import com.example.masternewsapplication.views.fragment.ItemDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter  of  recycler view  which have listner and set content to recycler view .
 */
public class SimpleItemRecyclerViewAdapter extends
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final ItemListActivity mParentActivity;
    private final List<Articles> mArcticalValue;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Articles articles = (Articles) view.getTag();
            if (mTwoPane) {
                Bundle bundle = new Bundle();
                //bundle.putString(ItemDetailFragment.ARG_ITEM_ID, articles.getUrl());
                bundle.putSerializable(ItemDetailFragment.ARG_ITEM_ID, articles);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(bundle);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Bundle bundle = new Bundle();
                bundle.putSerializable(ItemDetailFragment.ARG_ITEM_ID, articles);
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        }
    };

    /**
     * Constructor of adapter
     *
     * @param parent  object of  ItemListActivity launching Activity
     * @param items   object of Artical list
     * @param twoPane which take true and false value
     */
    public SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                         List<Articles> items,
                                         boolean twoPane) {
        mArcticalValue = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    /**
     * View holder class to hold views
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView newsImage;

        ViewHolder(View view) {
            super(view);
            newsImage = view.findViewById(R.id.iv_list_item_news_image);
            title = (TextView) view.findViewById(R.id.tv_list_content_title);
        }
    }

    @NonNull
    @Override
    public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new SimpleItemRecyclerViewAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.title.setText(mArcticalValue.get(position).getTitle());

        RequestOptions requestOptions = new RequestOptions();
//        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
//        Glide.with(holder.newsImage.getContext())
//                .load(mArcticalValue.get(position).getUrlToImage())
//                .apply(requestOptions.transforms( new RoundedCorners(16)))
//                .into(holder.newsImage);

        Picasso.with(holder.newsImage.getContext())
                .load(mArcticalValue.get(position).getUrlToImage())
                .fit()
                .into(holder.newsImage);

        holder.itemView.setTag(mArcticalValue.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);

    }

    @Override
    public int getItemCount() {
        return mArcticalValue.size();
    }
}

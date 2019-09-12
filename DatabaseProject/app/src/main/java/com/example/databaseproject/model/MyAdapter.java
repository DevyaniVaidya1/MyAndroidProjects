package com.example.databaseproject.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.databaseproject.R;

import java.util.ArrayList;

public class MyAdapter extends Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ContactDiary> mContactList;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    /**
     * INTERFACE FOR RECYCLER VIEW
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * long click listener
     */
    public interface OnItemLongClickListener {
        void OnItemLongClick(int position);
    }

    public void setOnLongClickListener(OnItemLongClickListener longClickListener) {
        mOnItemLongClickListener = longClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView textNumber;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView, final OnItemLongClickListener listener) {
            super(itemView);
            this.textName = itemView.findViewById(R.id.textcontactname);
            this.textNumber = itemView.findViewById(R.id.textcontactnumber);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null) {
                        int adapterPosition = getAdapterPosition();
                        Log.d("myAdapter", "onLongClick: " + adapterPosition);
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.OnItemLongClick(adapterPosition);
                            return true;
                        }
                    }
                    return false;
                }

            });

        }
    }

    public MyAdapter(ArrayList<ContactDiary> mContactList) {
        this.mContactList = mContactList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_listview_layout, parent,
                false);
        ViewHolder viewHolder = new ViewHolder(listItem, mOnItemLongClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final ContactDiary myListData = mContactList.get(position);
        holder.textName.setText(myListData.getmContactName());
        holder.textNumber.setText(myListData.getmContactNumber());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }


}

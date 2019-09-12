package com.example.contentprovidesession2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private ArrayList<Contacts> mlistData;
    private OnItemClickListener mListener;

    /**
     * INTERFACE FOR RECYCLER VIEW
     */
    public interface OnItemClickListener {
        void onItemClick(int position, TextView textView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView textlogo;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.textName = itemView.findViewById(R.id.textcontactname);
            this.textlogo = itemView.findViewById(R.id.textcontactimage);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, textlogo);
                        }
                    }
                }
            });
        }
    }

    public MyListAdapter(ArrayList<Contacts> mlistData) {
        this.mlistData = mlistData;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_list_item_layout, parent,
                false);
        ViewHolder viewHolder = new ViewHolder(listItem, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        final Contacts myListData = mlistData.get(position);
        holder.textName.setText(myListData.getmContactName());
        //holder.textNumber.setText(myListData.getmContactNumber());
        holder.textlogo.setText(myListData.getmFirstLetterOfName());
    }

    @Override
    public int getItemCount() {
        return mlistData.size();
    }


}

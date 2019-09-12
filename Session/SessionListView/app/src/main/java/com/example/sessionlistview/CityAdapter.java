package com.example.sessionlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {
    //create oject of  context and arraylist
    Context context;
    ArrayList<City> citieslist;


    public CityAdapter(Context context, ArrayList<City> citieslist) {
        this.context = context;
        this.citieslist = citieslist;
    }

    @Override
    public int getCount() {
        return citieslist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolderItem {
        TextView textViewItem;
    }

    @Override
    public View getView(int postion, View view, ViewGroup viewGroup) {

       ViewHolderItem viewHolderItem;

    if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_cities_list, viewGroup,false);
            viewHolderItem =new ViewHolderItem();
            viewHolderItem.textViewItem=view.findViewById(R.id.tvcities);
            view.setTag(viewHolderItem);
        }else {
        viewHolderItem= (ViewHolderItem) view.getTag();
    }

    viewHolderItem.textViewItem.setText((citieslist.get(postion).getCityName()));
        return view;

    }
}

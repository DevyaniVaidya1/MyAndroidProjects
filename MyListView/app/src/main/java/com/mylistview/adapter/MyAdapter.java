package com.mylistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylistview.R;
import com.mylistview.model.RowData;

import java.util.List;

/**
 * Created by SHARAD on 11/2/2017.
 */

public class MyAdapter extends BaseAdapter
{
    Context applicationContext;
    List<RowData> rowDatas;

    public MyAdapter(Context applicationContext, List<RowData> rowDatas) {
    this.applicationContext=applicationContext;
        this.rowDatas=rowDatas;
    }

    @Override
    public int getCount() {
        return rowDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return rowDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class MyViewHolder
    {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder=null;
          if(view==null)
          {
              LayoutInflater layoutInflater=(LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              view=layoutInflater.inflate(R.layout.row_data,null);
              myViewHolder=new MyViewHolder();
              myViewHolder.imageView=(ImageView) view.findViewById(R.id.list_image);
              myViewHolder.textView1=(TextView) view.findViewById(R.id.title);
              myViewHolder.textView2=(TextView) view.findViewById(R.id.subtilte);
              myViewHolder.textView3=(TextView) view.findViewById(R.id.title_version);
              RowData rowData=rowDatas.get(i);
              
              myViewHolder.imageView.setImageResource(rowData.getImg_title());
              myViewHolder.textView1.setText(rowData.getMain_title());
              myViewHolder.textView2.setText(rowData.getSub_title());
              myViewHolder.textView3.setText(rowData.getVersion_title());
              view.setTag(myViewHolder);

          }
        else
          {
              myViewHolder=(MyViewHolder)view.getTag();
          }

        return view;
    }
}

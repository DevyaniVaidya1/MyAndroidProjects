package com.mylistview;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mylistview.adapter.MyAdapter;
import com.mylistview.model.RowData;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener
{

    ListView listView;
    List<RowData> rowDatas;

    String main_tile[],sub_title[],verrsion_tilte[];
    TypedArray img_tile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        rowDatas=new ArrayList<RowData>();
        main_tile=getResources().getStringArray(R.array.main_title);
        sub_title=getResources().getStringArray(R.array.sub_title);
        verrsion_tilte=getResources().getStringArray(R.array.version_title);
        img_tile=getResources().obtainTypedArray(R.array.image_title);

     for(int i=0;i<main_tile.length;i++)
     {
         RowData rowData=new RowData(main_tile[i],sub_title[i],verrsion_tilte[i],img_tile.getResourceId(i,-1));
         rowDatas.add(rowData);
     }

        MyAdapter myAdapter=new MyAdapter(getApplicationContext(),rowDatas);
        myAdapter.notifyDataSetChanged();
        listView.setAdapter(myAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast t=Toast.makeText(this, "Iteam"+(i+1)+":"+rowDatas.get(i), Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        t.show();
        Intent intent=new Intent(MainActivity.this,MoreInfo.class);
        intent.putExtra("data",rowDatas.get(i));
        startActivity(intent);

    }
}

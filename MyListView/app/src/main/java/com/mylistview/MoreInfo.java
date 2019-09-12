package com.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylistview.model.RowData;

import org.w3c.dom.Text;

public class MoreInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        TextView textView1=(TextView)findViewById(R.id.textView);
        TextView textView2=(TextView)findViewById(R.id.textView2);
        TextView textView3=(TextView)findViewById(R.id.textView3);
         Bundle bundle=getIntent().getExtras();
           RowData rowData =(RowData)bundle.getSerializable("data");

        imageView.setImageResource(rowData.getImg_title());
        textView1.setText(rowData.getMain_title());
        textView2.setText(rowData.getSub_title());
        textView3.setText(rowData.getVersion_title());



    }
}

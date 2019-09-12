package com.example.employeelistview.adpter;
/**
 * adapter class which is bridge beetween listview and arraylist
 * in this adapter view holder pattern is use
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.employeelistview.R;
import com.example.employeelistview.model.EmployeeRowData;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {
    //create oject of  context and arraylist
   private  Context context;
   private ArrayList<EmployeeRowData> employeeRowData;

    /***
     *
     * @param context         instance of context
     * @param employeeRowData data set for listview
     */
    public EmployeeAdapter(Context context, ArrayList<EmployeeRowData> employeeRowData) {
        this.context = context;
        this.employeeRowData = employeeRowData;
    }

    @Override
    public int getCount() {
        return employeeRowData.size();
    }

    @Override
    public Object getItem(int i) {
        return employeeRowData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    //view holder
    static class ViewHolderItem {
        TextView textViewEmployeeName;
        TextView textViewEmployeeNumber;
        ImageView imgEmployeeImage;
    }

    @Override
    public View getView(int position , View convertedView, ViewGroup viewGroup) {

        ViewHolderItem viewHolderItem;

        if (convertedView == null) {
            convertedView = LayoutInflater.from(context).inflate(R.layout.activity_employee_listview,
                    viewGroup,false);
            //allocate memory to object of viewholder
            viewHolderItem =new ViewHolderItem();
            //get id of views like textviews, img view
            viewHolderItem.textViewEmployeeName=convertedView.findViewById(R.id.txt_employeename);
            viewHolderItem.textViewEmployeeNumber=convertedView.findViewById(R.id.txt_employeenumber);
            viewHolderItem.imgEmployeeImage=convertedView.findViewById(R.id.img_employeeimage);
            convertedView.setTag(viewHolderItem);
        }else {
            viewHolderItem= (ViewHolderItem) convertedView.getTag();
        }

        // return object of employeeRowdata
        EmployeeRowData rowdataObject= employeeRowData.get(position);
        //now  use object name instead of arrayname employeeRowData
        viewHolderItem.imgEmployeeImage.setImageResource(rowdataObject.getImage());
        viewHolderItem.textViewEmployeeNumber.setText((rowdataObject.getContactNumber()));
        viewHolderItem.textViewEmployeeName.setText((rowdataObject.getEmployeeName()));

        return convertedView;
    }
}

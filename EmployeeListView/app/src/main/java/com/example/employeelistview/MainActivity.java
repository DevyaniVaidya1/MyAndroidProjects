package com.example.employeelistview;
/**
 *   this class hold array , adapter information and listview listener
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeelistview.adpter.EmployeeAdapter;
import com.example.employeelistview.model.EmployeeRowData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   private ListView listView;
    ArrayList<EmployeeRowData> employeeRowData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get id of listview
        listView = findViewById(R.id.listView);
        //add data to class
        employeeRowData.add(new EmployeeRowData("Devyani Vaidya",
                "8600419145", "40000", "Wani", R.drawable.girl));
        employeeRowData.add(new EmployeeRowData("Divya Vaidya",
                "8600419145", "40000", "Kailash Nagar", R.drawable.usericon2));
        employeeRowData.add(new EmployeeRowData("Asmita Bongulvar",
                "8600419145", "40000", "Shegao", R.drawable.usericon3));
        employeeRowData.add(new EmployeeRowData("Tejaswi Pote",
                "9561032719", "40000", "Amravati", R.drawable.usericon4));
        employeeRowData.add(new EmployeeRowData("Kartik Kalpande",
                "1234567890", "40000", "Amravati", R.drawable.boy));
        employeeRowData.add(new EmployeeRowData("Anurag ",
                "1234567890", "40000", "Nagpur", R.drawable.usericon5));
        employeeRowData.add(new EmployeeRowData("Uday Naitam",
                "1234567890", "40000", "Amravati", R.drawable.usericon1));
        employeeRowData.add(new EmployeeRowData("Devyani Vaidya",
                "8600419145", "40000", "Wani", R.drawable.girl));
        employeeRowData.add(new EmployeeRowData("Divya Vaidya",
                "8600419145", "40000", "Kailash Nagar", R.drawable.usericon2));
        employeeRowData.add(new EmployeeRowData("Asmita Bongulvar",
                "8600419145", "40000", "Shegao", R.drawable.usericon3));
        employeeRowData.add(new EmployeeRowData("Tejaswi Pote",
                "9561032719", "40000", "Amravati", R.drawable.usericon4));
        employeeRowData.add(new EmployeeRowData("Vaibhav Vaidya",
                "1234567890", "40000", "Amravati", R.drawable.boy));
        employeeRowData.add(new EmployeeRowData("Kartik Kalpande",
                "1234567890", "40000", "Amravati", R.drawable.boy));
        employeeRowData.add(new EmployeeRowData("Anurag ",
                "1234567890", "40000", "Nagpur", R.drawable.usericon5));
        employeeRowData.add(new EmployeeRowData("Uday Naitam",
                "1234567890", "40000", "Amravati", R.drawable.usericon1));
        employeeRowData.add(new EmployeeRowData("Devyani Vaidya",
                "8600419145", "40000", "Wani", R.drawable.girl));
        employeeRowData.add(new EmployeeRowData("Divya Vaidya",
                "8600419145", "40000", "Kailash Nagar", R.drawable.usericon2));
        employeeRowData.add(new EmployeeRowData("Asmita Bongulvar",
                "8600419145", "40000", "Shegao", R.drawable.usericon3));
        employeeRowData.add(new EmployeeRowData("Tejaswi Pote",
                "9561032719", "40000", "Amravati", R.drawable.usericon4));
        employeeRowData.add(new EmployeeRowData("Vaibhav Vaidya",
                "1234567890", "40000", "Amravati", R.drawable.boy));

        //create instence of EmployeeAdapter
        final EmployeeAdapter employeeAdapter = new EmployeeAdapter(this, employeeRowData);
        //connect listview and  adapter
        listView.setAdapter(employeeAdapter);
        //listener of listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // className object= classonjectname .get(postion of item);
                EmployeeRowData employee = employeeRowData.get(position);
                //get value of each view
                // Getting listview click value into String variable.
                String name = employee.getEmployeeName();
                String salary = employee.getSalary();
                String contact = employee.getContactNumber();
                String city = employee.getCity();
                int imgId = employee.getImage();
                //putting
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("salary", salary);
                bundle.putString("contact", contact);
                bundle.putString("city", city);
                bundle.putInt("imgId", imgId);
                // tell which is next activity
                Intent intent = new Intent(MainActivity.this, EmployeeInformationActivity.class);
                // Sending value to another activity using intent.
                intent.putExtras(bundle);
                // intent.putExtra("ListViewClickedValue",+employeeAdapter.getItemId(position));
                startActivity(intent);
            }
        });
    }
}

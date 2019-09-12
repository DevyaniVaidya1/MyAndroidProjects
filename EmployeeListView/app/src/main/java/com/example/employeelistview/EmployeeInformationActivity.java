package com.example.employeelistview;
/**
 * this class to display information of Employee
 */

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeInformationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_information);
        //actual code
         TextView mtextName;
         TextView mtextSalary;
         TextView mtextContact;
         TextView mtextCity;
         ImageView mimageUser;
        //find id's iof view
        mtextName = findViewById(R.id.txt_name);
        mtextCity = findViewById(R.id.txt_city);
        mtextContact = findViewById(R.id.txt_number);
        mtextSalary = findViewById(R.id.txt_salary);
        mimageUser = findViewById(R.id.iv_profile);
        //bundle which hold any kind of data
        Bundle bundle = getIntent().getExtras();
        //get values from previous activity
        int id = bundle.getInt("imgId");
        String name = bundle.getString("name");
        String city = bundle.getString("city");
        String salary = bundle.getString("salary");
        String contact = bundle.getString("contact");
        // Setting up received value to view
        mimageUser.setImageResource(id);
        mtextSalary.setText(salary);
        mtextContact.setText(contact);
        mtextName.setText(name);
        mtextCity.setText(city);

    }
}

package com.example.sessionlistview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listOfCities;
    //array list of pogo class City
    ArrayList<City> cities = new ArrayList<City>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//instence of ListView
        listOfCities = findViewById(R.id.listOfCities);
//add data to array
        cities.add(new City("wani"));
        cities.add(new City("Nagpur"));
        cities.add(new City("Pune"));
        cities.add(new City("Mumbai"));
        cities.add(new City("wani"));
        cities.add(new City("Nagpur"));
        cities.add(new City("Pune"));
        cities.add(new City("Mumbai"));
        cities.add(new City("wani"));
        cities.add(new City("Nagpur"));
        cities.add(new City("Pune"));
        cities.add(new City("Mumbai"));
        cities.add(new City("wani"));
        cities.add(new City("Nagpur"));
        cities.add(new City("Pune"));
        cities.add(new City("Mumbai"));
        cities.add(new City("wani"));
        cities.add(new City("Nagpur"));
        cities.add(new City("Pune"));
        cities.add(new City("Mumbai"));
        cities.add(new City("wani"));
        cities.add(new City("Nagpur"));
        cities.add(new City("Pune"));
        cities.add(new City("Mumbai"));
        cities.add(new City("wani"));
        cities.add(new City("Nagpur"));
        cities.add(new City("Pune"));
        cities.add(new City("Mumbai"));
        //create instence of CityAdapter
        CityAdapter cityadapter = new CityAdapter(this, cities);
        //link adapter with list view
        listOfCities.setAdapter(cityadapter);

//listener of listview
//listOfCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//});

    }
}

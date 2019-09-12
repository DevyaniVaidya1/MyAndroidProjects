package com.example.weather;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.weather.location.mypreference;

public class home extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    SharedPreferences sharedpreferences;
    TextView t1_temp,t2_city,t3_description,t4_date;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView locationText, time;
        SQLiteDatabase db1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        b1 = (Button) findViewById(R.id.set_location);
        t1_temp=(TextView)findViewById(R.id.temp);
        t2_city=(TextView)findViewById(R.id.location);
        t3_description=(TextView)findViewById(R.id.status);
        t4_date=(TextView)findViewById(R.id.date);


        find_weather();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, location.class);
                startActivity(intent);

            }
        });
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        getLocation();

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            //t1.setText( addresses.get(0).getAddressLine(0) + ", " +
                 //  addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
        } catch (Exception e) {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(home.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }
    public void find_weather(){
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        String cName =sharedpreferences.getString("LOC", "");
        final String url="https://openweathermap.org/data/2.5/weather?q=" +cName+"&appid=b6907d289e10d714a6e88b30761fae22";

        JsonObjectRequest jor= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array=response.getJSONArray("weather");
                    JSONObject object=array.getJSONObject(0);
                    String temp=String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");
                    t1_temp.setText(temp);
                    t2_city.setText(city);
                    t3_description.setText(description);


                    Calendar calender= Calendar.getInstance();
                    SimpleDateFormat sdt= new SimpleDateFormat("YYY/MM/DD");
                    String formate_date=sdt.format(calender.getTime());
                    t4_date.setText(formate_date);

                    /*double temp_int=Double.parseDouble(temp);
                    double centi=(temp_int-32)/1.8000;
                    centi=Math.round(centi);
                    int i=(int)centi;*/
                    double temp_int=Double.parseDouble(temp);
                    int i=(int)temp_int;

                   t1_temp.setText(String.valueOf(i+"*c"));
                    //Toast.makeText(home.this,""+i,Toast.LENGTH_LONG).show();




                    //from another
                    JSONObject jsonObject = new JSONObject(url);
                    String weatherData = jsonObject.getString("weather");
                    String mainTemperature = jsonObject.getString("main"); //this main is not part of weather array, it's seperate variable like weather
                    double visibility;
                    //            Log.i("weatherData",weatherData);
                    //weather data is in Array
                    JSONArray array1 = new JSONArray(weatherData);

                    String main = "";
                    String description1 = "";
                    String temperature = "";

                    for(int i1=0; i1<array.length(); i1++){
                        JSONObject weatherPart = array.getJSONObject(i1);
                        main = weatherPart.getString("main");
                        description1 = weatherPart.getString("description");
                    }

                    JSONObject mainPart = new JSONObject(mainTemperature);
                    temperature = mainPart.getString("temp");

                    visibility = Double.parseDouble(jsonObject.getString("visibility"));
                    //By default visibility is in meter
                    int visibiltyInKilometer = (int) visibility/1000;

                    Log.i("Temperature",temperature);

            /*Log.i("main",main);
            Log.i("description",description);*/

                    String resultText = "Main :                     "+main+
                            "\nDescription :        "+description1 +
                            "\nTemperature :        "+temperature +"*C"+
                            "\nVisibility :              "+visibiltyInKilometer+" KM";
                    String tempreture =temperature +"*C";
                    String describ =description ;
                    Toast.makeText(home.this,""+tempreture,Toast.LENGTH_LONG).show();







                }catch (JSONException e){
                    e.printStackTrace();
                    //Toast.makeText(home.this,""+e,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jor);
    }
}

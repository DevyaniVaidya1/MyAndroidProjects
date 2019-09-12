package com.example.uiapp;
/**
 * class to display information of user  and select random  image and send to  next activity
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Button mViewImg =findViewById(R.id.btnInfoViewImg);
        // fetch data from window one nd display it
        Intent intent =getIntent();
        String userName=intent.getStringExtra("UserName");
        String userPass =intent.getStringExtra("UserPassword");
        String userEmail =intent.getStringExtra("UserEmail");
        String userAbout =intent.getStringExtra("UserAbout");
        String dateOfBirth =intent.getStringExtra("Date");
        String time =intent.getStringExtra("Time");
        //declaration and find id
        TextView name  =findViewById(R.id.tvInfoUserName);
        TextView email  =findViewById(R.id.tvInfoUserEmail);
        TextView about=findViewById(R.id.tvAbout);
        TextView dateOfBirthOfUser=findViewById(R.id.tvDate);
        TextView timeUser=findViewById(R.id.tvtime);
        //if for data to display
        if (userEmail != null ) {
            name.setText("UserName: "+userName);
            email.setText("UserEmail: "+userEmail);
            about.setText("UserAbout :"+userAbout);
            dateOfBirthOfUser.setText("Date Of Birth :"+dateOfBirth);
            timeUser.setText("Time :"+time);
        }else {
            name.setText("UserName: "+userName);
        }

        mViewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //array of  image id
                int[] res={R.drawable.photo1,R.drawable.photo2,R.drawable.photo3,R.drawable.photo4};
                //onject of random class
                Random rand=new Random();
                //get random index
                int index = rand.nextInt(res.length);
                int imageID = res[index];
                Intent intent=new Intent(InformationActivity.this,ImageViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ImageID",imageID);
                 intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

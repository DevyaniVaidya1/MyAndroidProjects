package com.example.loginscreenmvp.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreenmvp.R;
import com.example.loginscreenmvp.SignIn;
import com.example.loginscreenmvp.login.activity.ILogin;
import com.example.loginscreenmvp.login.presenter.LoginPresenter;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  , ILogin {
 private Button mLogin;
 private TextView mName;
 private TextView mPassword;
 private TextView mRegistrationLink;
 private LoginPresenter mloginPresenter;
private LinearLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilization();
        mLogin.setOnClickListener(this);

    }

    /**
     * initialized variables
     */
    private void initilization(){
        mLogin=findViewById(R.id.btnLogin);
        mName=findViewById(R.id.et_name);
        mPassword=findViewById(R.id.et_passsword);
        mRegistrationLink=findViewById(R.id.tv_Registration);
        coordinatorLayout= findViewById(R.id.login_linearlayout);
        mloginPresenter=new LoginPresenter(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogin :
                if(validate())
                showMessage("Next Page");

            case R.id.tv_Registration :
                startActivity(new Intent(this , SignIn.class));
//            default:
//                    showMessage("Try after some time ");
        }
    }

    @Override
    public Boolean validate() {
       return mloginPresenter.validate(mName.getText().toString(), mPassword.getText().toString());

    }

    @Override
    public void showMessage(String message) {
//        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
//                .setAction("Action",  new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
//                        snackbar1.show();
//                    }
//                }).show();

        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG).setAction("action",null).show();
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

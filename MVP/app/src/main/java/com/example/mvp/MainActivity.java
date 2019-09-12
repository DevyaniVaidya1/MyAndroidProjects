package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IView{

    private IContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new Presenter(this);
        mPresenter.onLoginButtonClicked("Hello");
    }

    @Override
    public void onEditTextEmptyError() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userSavedToDatabase() {

    }

    @Override
    public void onDataLoaded() {

    }

    @Override
    public void showLoading() {

    }
}

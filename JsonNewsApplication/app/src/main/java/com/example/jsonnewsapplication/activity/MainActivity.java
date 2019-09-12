package com.example.jsonnewsapplication.activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonnewsapplication.R;
import com.example.jsonnewsapplication.adapter.NewsAdapter;
import com.example.jsonnewsapplication.model.Articles;
import com.example.jsonnewsapplication.model.FetchData;
import com.example.jsonnewsapplication.model.NewsData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity  {
    public NewsData mNewsdata = new NewsData();
    private List<Articles> mArticals = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * CALL ASYNCH TASK  CALL
         */
        try {
            mNewsdata = new FetchData().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setRecyclerView();

    }

    /**
     * FUNCTION USE TO SET RECYCLER VIEW TO ADAPTER AND WITH ARRAY
     */
    private void setRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mArticals = mNewsdata.getArticles();
        NewsAdapter adapter = new NewsAdapter(this, mArticals);
        mRecyclerView.setAdapter(adapter);
    }




}

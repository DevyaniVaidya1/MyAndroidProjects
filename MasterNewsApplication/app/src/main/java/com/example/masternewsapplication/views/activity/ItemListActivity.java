package com.example.masternewsapplication.views.activity;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masternewsapplication.IDataResponderListener;
import com.example.masternewsapplication.R;
import com.example.masternewsapplication.adapter.SimpleItemRecyclerViewAdapter;
import com.example.masternewsapplication.model.Articles;
import com.example.masternewsapplication.model.FetchDataFromServer;
import com.example.masternewsapplication.model.NewsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements IDataResponderListener {
    private static List<Articles> mArticals = new ArrayList<>();
    private NewsData mNewsData = new NewsData();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        //callLocalization();
        setToolBar();
        callAsycTask();
        if (findViewById(R.id.item_detail_container) != null) {
            /**
             *   The detail container view will be present only in the
             *              large-screen layouts (res/values-w900dp).
             *              If this view is present, then the
             *              activity should be in two-pane mode.
             */
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
     * Set recycler view with adapter .
     * Set adapter to dataset.
     *
     * @param recyclerView which display data on activity
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, mArticals, mTwoPane));
    }

    /**
     * To show progress bar on list while fetching data
     */
    @Override
    public void onPreExecute() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Downloading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }


    /**
     * CALL ASYNC TASK  CALL
     */
    private void callAsycTask() {

        FetchDataFromServer process = new FetchDataFromServer(this);
        try {
            mNewsData = process.execute().get();
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mArticals = mNewsData.getArticles();

    }

    /**
     * Code to set toolbar
     */
    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }


}

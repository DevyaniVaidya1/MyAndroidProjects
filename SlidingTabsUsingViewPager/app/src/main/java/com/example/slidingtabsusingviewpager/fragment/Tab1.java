package com.example.slidingtabsusingviewpager.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slidingtabsusingviewpager.R;
import com.example.slidingtabsusingviewpager.adapters.RecyclerViewAdapter;
import com.example.slidingtabsusingviewpager.api.GetMoviesInterface;
import com.example.slidingtabsusingviewpager.api.RetrofitClient;
import com.example.slidingtabsusingviewpager.model.MarvelMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Tab1 extends Fragment {
    public static final String TAG = "Tab1";
    private RecyclerView mrecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<MarvelMovie> mMovieList;
    private View view;

    public Tab1() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieList = new ArrayList<>();
        callToApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * method to call api and initialize list
     */
    private void callToApi() {

        /** Create handle for the RetrofitInstance interface*/
        GetMoviesInterface service = RetrofitClient.getRetrofitInstance().create(GetMoviesInterface.class);
        Call<List<MarvelMovie>> call = service.getAllData();
        call.enqueue(new Callback<List<MarvelMovie>>() {
            @Override
            public void onResponse(Call<List<MarvelMovie>> call, Response<List<MarvelMovie>> response) {
                mMovieList = response.body();
                Toast.makeText(getActivity(), String.valueOf(response.body().size()) +
                        "response size " + mMovieList.size(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: " + mMovieList.get(0).getName());
                Log.d(TAG, "size of array list " + mMovieList.size());
                setRecyclerViewAndAdapter();
            }

            @Override
            public void onFailure(Call<List<MarvelMovie>> call, Throwable t) {
                Toast.makeText(getActivity(),
                        "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * to set the recycler view adapter and list to adapter
     */
    private void setRecyclerViewAndAdapter() {
        mrecyclerView = view.findViewById(R.id.recyclerview);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Log.d(TAG, "setRecyclerViewAndAdapter: "+mMovieList.size());
        mRecyclerViewAdapter = new RecyclerViewAdapter(mMovieList, getActivity());
        mrecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

}
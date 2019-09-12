package com.example.mweather.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mweather.R;
import com.example.mweather.adapters.WeatherAdapter;
import com.example.mweather.api.NetworkService;
import com.example.mweather.models.WeatherData;
import com.example.mweather.models.WeatherItem;
import com.example.mweather.utils.EmptyRecyclerView;
import com.example.mweather.utils.UiUtils;
import com.example.mweather.utils.WeatherPreferences;
import com.example.mweather.utils.WeatherUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * This class displays the weather for added cities
 */
public class WeatherFragment extends Fragment {
    private FloatingActionButton mFabAddWeatherItem;
    private WeatherAdapter mAdapter;
    private AddWeatherDialogFragment mAddWeatherDialogFragment;
    public static final String TAG = WeatherFragment.class.getSimpleName();
    private final ArrayList<WeatherItem> mWeatherItems = new ArrayList<>();
    private boolean isDoingRequest = false;
    private boolean isShownError = false;
    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.weather_recycler_view)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.loading_weather)
    ProgressBar mLoadingIndicator;
    private boolean isCelciusTemp = true;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAdapter = new WeatherAdapter(getActivity(), new ArrayList<WeatherItem>(), new WeatherListener() {
            @Override
            public void onWeatherTrashTouched(int position) {
                mWeatherItems.remove(position);
                ArrayList<String> weatherCities = WeatherPreferences.getInstance(getContext()).getWeatherCities();
                weatherCities.remove(position);
                WeatherPreferences.getInstance(getContext()).saveWeatherCities(weatherCities);
                showWeatherItems(mWeatherItems);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);

        //mFabAddWeatherItem = getActivity().findViewById(R.id.fab_add_weather_item);
        mFabAddWeatherItem = view.findViewById(R.id.fab_add_weather_item);
        mFabAddWeatherItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddWeatherItem();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));//horizontal scroll
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setEmptyView(emptyView);


        loadWeatherItems();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.d(TAG, "onOptionsItemSelected: ");
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.contentFrame, new WeatherSettingsFragment())
//                        .addToBackStack(null)
//                        .commit();
                UiUtils.replaceFragmentToActivity(getActivity().getSupportFragmentManager(), new WeatherSettingsFragment(), R.id.contentFrame, true);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * display add city dialog
     */
    public void showAddWeatherItem() {
        if (mAddWeatherDialogFragment == null) {
            mAddWeatherDialogFragment = AddWeatherDialogFragment.newInstance();
            mAddWeatherDialogFragment.setTargetFragment(this, 0);
        }
        mAddWeatherDialogFragment.show(getFragmentManager(), "addWeatherDialog");
    }

    /**
     * Updates the recycler view with latest data list
     *
     * @param weatherItemsList
     */
    public void showWeatherItems(ArrayList<WeatherItem> weatherItemsList) {
        //store the data in sharedpreference if offline data is enabled
        if (isOfflineDataEnabled()) {
            WeatherPreferences.getInstance(getContext()).saveOfflineCitiesWeatherData(weatherItemsList);
        }
        mAdapter.swap(weatherItemsList);
    }

    /**
     * Reloads the recycleview
     */
    public void addCityDialogDismiss() {
        loadWeatherItems();
    }

    /**
     * To handle on delete icon click
     */
    public interface WeatherListener {
        void onWeatherTrashTouched(int id);
    }

    /**
     * Weather to display loading indicator
     *
     * @param isVisible
     */
    public void displayLoadingIndicator(Boolean isVisible) {
        mLoadingIndicator.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(isVisible ? View.GONE : View.VISIBLE);
    }

    /**
     * Show snack bar with network error
     */
    public void displayNoNetworkMessage() {
        if (getView() != null) {
            Snackbar snackbarNoInternetConnection = Snackbar
                    .make(getView(), getString(R.string.error_msg_internet), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadWeatherItems();
                        }
                    });
            snackbarNoInternetConnection.show();

            ArrayList<WeatherItem> weatherItemsList = WeatherPreferences.getInstance(getContext()).getOfflineCitiesWeatherData();
            if (weatherItemsList != null) {
                showWeatherItems(weatherItemsList);
            }
        }
    }

    /**
     * Get the selected setting by user whether offline data enabled or not
     * @return
     */
    private boolean isOfflineDataEnabled() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getBoolean("offline_data", false);
    }

    /**
     * Gets the list of added cities and fetch weather for each by making API call
     */
    public void loadWeatherItems() {
        Log.d(TAG, "loadWeatherItems: ");
        if (isAdded() && !isDoingRequest) {
            ArrayList<String> weatherCities = WeatherPreferences.getInstance(getContext()).getWeatherCities();
            if (weatherCities.size() == 0) {
                showWeatherItems(mWeatherItems);
                return;
            }
            isDoingRequest = true;
            isShownError = false;
            displayLoadingIndicator(true);
            mWeatherItems.clear();

            for (String weatherCity : weatherCities) {
                Call<WeatherData> call = NetworkService.getInstance(getContext()).getWeatherAPI().getWeatherCity(weatherCity);
                call.enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, retrofit2.Response<WeatherData> response) {
                        if (response.isSuccessful()) {
                            WeatherData currentWeatherData = response.body();
                            String city = "";
                            String img = "";
                            double kelvin = 0;
                            int humidty = 0;
                            float windSpeed = 0;
                            float rainLevel = 0;
                            String temp = "0";
                            if (currentWeatherData != null) {
                                city = currentWeatherData.name;
                                if (currentWeatherData.weather != null) {
                                    if (currentWeatherData.weather.size() > 0)
                                        img = currentWeatherData.weather.get(0).icon;
                                }

                                isCelciusTemp = getSelectedTempFormat();
                                if (currentWeatherData.main != null) {
                                    kelvin = (currentWeatherData.main.temp == null) ? 0 : currentWeatherData.main.temp;

                                    if (isCelciusTemp) {
                                        kelvin = WeatherUtil.convertKelvinInCelsius(kelvin);
                                        temp = WeatherUtil.round((float) kelvin, 2) + " \u2103";
                                    } else {
                                        kelvin = WeatherUtil.convertKelvinInFar(kelvin);
                                        temp = WeatherUtil.round((float) kelvin, 2) + " \u2109";
                                    }
                                    humidty = (currentWeatherData.main.humidity == null) ? 0 : currentWeatherData.main.humidity;
                                }
                                if (currentWeatherData.wind != null) {
                                    windSpeed = (currentWeatherData.wind.speed == null) ? 0 : currentWeatherData.wind.speed;
                                }
                                if (currentWeatherData.rain != null) {
                                    rainLevel = (currentWeatherData.rain.rainLevelLastThreeHours == null) ? 0 : currentWeatherData.rain.rainLevelLastThreeHours;
                                }
                            }
                            //WeatherItem weatherItem = new WeatherItem(city, img, kelvin, humidty, rainLevel, windSpeed);
                            WeatherItem weatherItem = new WeatherItem(city, img, temp, humidty, rainLevel, windSpeed);
                            mWeatherItems.add(weatherItem);
                            showWeatherItems(mWeatherItems);
                        } else {
                            if (!isShownError) {
                                displayNoNetworkMessage();
                                isShownError = true;
                            }

                        }
                        isDoingRequest = false;
                        displayLoadingIndicator(false);
                    }

                    /**
                     * Get the selected setting by user whether to display temperature in Celsius or Fahrenheit
                     * @return
                     */
                    private boolean getSelectedTempFormat() {
                        SharedPreferences sharedPreferences =
                                PreferenceManager.getDefaultSharedPreferences(getActivity());
                        return sharedPreferences.getBoolean("temp_switch", true);
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {
                        isDoingRequest = false;
                        displayLoadingIndicator(false);
                        displayNoNetworkMessage();
                    }
                });
            }
        }

    }
}

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

import androidx.annotation.NonNull;
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
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * This class displays the weather for added cities
 */
public class WeatherFragment extends Fragment {
    private WeatherAdapter mAdapter;
    private AddWeatherDialogFragment mAddWeatherDialogFragment;
    private static final String TAG = WeatherFragment.class.getSimpleName();
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
        mAdapter = new WeatherAdapter(Objects.requireNonNull(getActivity()),
                new ArrayList<>(),
                position -> {
                    mWeatherItems.remove(position);
                    ArrayList<String> weatherCities = WeatherPreferences
                            .getInstance(getContext())
                            .getWeatherCities();
                    weatherCities.remove(position);
                    WeatherPreferences.getInstance(getContext()).saveWeatherCities(weatherCities);
                    showWeatherItems(mWeatherItems);
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        FloatingActionButton mFabAddWeatherItem = view.findViewById(R.id.fab_add_weather_item);
        mFabAddWeatherItem.setOnClickListener(view1 -> showAddWeatherItem());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, true));//Horizontal Scroll
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
        if (item.getItemId() == R.id.action_settings) {
            Log.d(TAG, "onOptionsItemSelected: ");
            UiUtils.replaceFragmentToActivity(Objects.requireNonNull(getActivity())
                            .getSupportFragmentManager()
                    , new WeatherSettingsFragment()
                    , R.id.contentFrame
                    , true);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * display add city dialog fragment
     */
    public void showAddWeatherItem() {
        if (mAddWeatherDialogFragment == null) {
            mAddWeatherDialogFragment = AddWeatherDialogFragment.newInstance();
            mAddWeatherDialogFragment.setTargetFragment(this, 0);
        }
        assert getFragmentManager() != null;
        mAddWeatherDialogFragment.show(getFragmentManager(), "addWeatherDialog");
    }

    /**
     * Updates the recycler view with latest data list
     */
    public void showWeatherItems(ArrayList<WeatherItem> weatherItemsList) {
        // store the data in shared preference if offline data is enabled

        if (isOfflineDataEnabled()) {
            WeatherPreferences.getInstance(getContext()).saveOfflineCitiesWeatherData(weatherItemsList);
        }
        mAdapter.swap(weatherItemsList);
    }

    /**
     * Reloads the recycle view
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
     * @param isVisible state
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

            ArrayList<WeatherItem> weatherItemsList = WeatherPreferences.
                    getInstance(getContext())
                    .getOfflineCitiesWeatherData();
            if (weatherItemsList != null) {
                showWeatherItems(weatherItemsList);
            }
        }
    }

    /**
     * Get the selected setting by user whether offline data enabled or not
     */
    private boolean isOfflineDataEnabled() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getActivity()));
        return sharedPreferences.getBoolean("offline_data", false);
    }

    /**
     * Gets the list of added cities and fetch weather for each by making API call
     */
    public void loadWeatherItems() {
        Log.d(TAG, "loadWeatherItems: ");
        if (isAdded() && !isDoingRequest) {
            ArrayList<String> weatherCities = WeatherPreferences
                    .getInstance(getContext())
                    .getWeatherCities();
            if (weatherCities.size() == 0) {
                showWeatherItems(mWeatherItems);
                return;
            }
            isDoingRequest = true;
            isShownError = false;
            displayLoadingIndicator(true);
            mWeatherItems.clear();

            for (String weatherCity : weatherCities) {
                Call<WeatherData> call = NetworkService.getInstance(getContext())
                        .getWeatherAPI()
                        .getWeatherCity(weatherCity);
                call.enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call,
                                           retrofit2.Response<WeatherData> response) {
                        if (response.isSuccessful()) {
                            WeatherData currentWeatherData = response.body();
                            String city = "";
                            String img = "";
                            double kelvin = 0;
                            int humidty = 0;
                            float windSpeed = 0;
                            float rainLevel = 0;
                            String description = "";
                            String temp = "0";

                            if (currentWeatherData != null) {
                                city = currentWeatherData.name;
                                if (currentWeatherData.weather != null) {
                                    if (currentWeatherData.weather.size() > 0) {
                                        img = currentWeatherData.weather.get(0).icon;
                                        description = currentWeatherData.weather.get(0).description;
                                    }
                                }

                                isCelciusTemp = getSelectedTempFormat();
                                if (currentWeatherData.main != null) {
                                    kelvin = (currentWeatherData.main.temp == null)
                                            ? 0 : currentWeatherData.main.temp;

                                    if (isCelciusTemp) {
                                        kelvin = WeatherUtil.convertKelvinInCelsius(kelvin);
                                        temp = WeatherUtil.round((float) kelvin, 2)
                                                + " \u2103";//unicode character degree CELSIUS
                                    } else {
                                        kelvin = WeatherUtil.convertKelvinInFahrenheit(kelvin);
                                        temp = WeatherUtil.round((float) kelvin, 2)
                                                + " \u2109"; // unicode character degree FAHRENHEIT
                                    }
                                    humidty = (currentWeatherData.main.humidity == null)
                                            ? 0 : currentWeatherData.main.humidity;
                                }
                                if (currentWeatherData.wind != null) {
                                    windSpeed = (currentWeatherData.wind.speed == null)
                                            ? 0 : currentWeatherData.wind.speed;
                                }
                                if (currentWeatherData.rain != null) {
                                    rainLevel = (currentWeatherData.rain.rainLevelLastThreeHours == null)
                                            ? 0 : currentWeatherData.rain.rainLevelLastThreeHours;
                                }
                            }
                            /*WeatherItem weatherItem =
                            new WeatherItem(city, img, kelvin, humidty, rainLevel, windSpeed,description); */
                            WeatherItem weatherItem = new WeatherItem(city, img, temp, humidty,
                                    rainLevel, windSpeed, description);
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
                     * Get the selected setting by user whether to
                     * display temperature in Celsius or Fahrenheit
                     */
                    private boolean getSelectedTempFormat() {
                        SharedPreferences sharedPreferences =
                                PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getActivity()));
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

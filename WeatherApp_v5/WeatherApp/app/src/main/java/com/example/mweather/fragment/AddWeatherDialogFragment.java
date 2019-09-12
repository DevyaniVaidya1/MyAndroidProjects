package com.example.mweather.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mweather.R;
import com.example.mweather.api.NetworkService;
import com.example.mweather.models.WeatherData;
import com.example.mweather.utils.WeatherPreferences;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;


public class AddWeatherDialogFragment extends DialogFragment {

    @BindView(R.id.et_city)
    EditText mEditTextCity;
    @BindView(R.id.loading_add_city)
    ProgressBar mLoadingIndicator;

    private Boolean isDoingRequest = false;

    /**
     * Creates the new instance of this dialog fragment
     * @return
     */
    public static AddWeatherDialogFragment newInstance() {
        return new AddWeatherDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_add_city, null);
        ButterKnife.bind(this, view);

        builder.setView(view)
                .setPositiveButton(R.string.add, null)
                .setNegativeButton(R.string.cancel, null);

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeAPICall(mEditTextCity.getText().toString());
                }
            });
        }
    }

    /**
     * Closes the add city dialog and loads the city weather data
     */
    public void closeView() {
        ((WeatherFragment) getTargetFragment()).addCityDialogDismiss();
        AddWeatherDialogFragment.this.getDialog().dismiss();
    }

    /**
     * Show error message for entered city
     * @param stringRessource
     */
    public void displayErrorMessage(int stringRessource) {
        mEditTextCity.setError(getString(stringRessource));
    }

    /**
     * shows loading indicator
     * @param isVisible
     */
    public void displayLoadingIndicator(Boolean isVisible) {
        mLoadingIndicator.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mEditTextCity.setVisibility(isVisible ? View.GONE : View.VISIBLE);
    }


    /**
     * Get the weather for entered city name and store it locally
     *
     * @param city
     */
    void makeAPICall(final String city) {

        if (isCityExists(city)) {
            displayErrorMessage(R.string.country_exists);
            return;
        }

        if (isAdded() && !isDoingRequest) {
            isDoingRequest = true;
            displayLoadingIndicator(true);
            Call<WeatherData> call = NetworkService.getInstance(getContext()).getWeatherAPI().getWeatherCity(city);
            call.enqueue(new Callback<WeatherData>() {
                @Override
                public void onResponse(Call<WeatherData> call, retrofit2.Response<WeatherData> response) {
                    if (response.isSuccessful()) {
                        if (response.body().cod == 200) {
                            ArrayList<String> weatherCities;
                            if (WeatherPreferences.getInstance(getContext()).getWeatherCities() != null)
                                weatherCities = WeatherPreferences.getInstance(getContext()).getWeatherCities();
                            else
                                weatherCities = new ArrayList<String>();
                            weatherCities.add(city.toUpperCase());
                            WeatherPreferences.getInstance(getContext()).saveWeatherCities(weatherCities);
                            closeView();
                        } else {
                            displayErrorMessage(R.string.country_not_found);
                        }
                    } else {
                        displayErrorMessage(R.string.country_not_found);
                    }
                    isDoingRequest = false;
                    displayLoadingIndicator(false);
                }

                @Override
                public void onFailure(Call<WeatherData> call, Throwable t) {
                    displayErrorMessage(R.string.error_msg_internet);
                    isDoingRequest = false;
                    displayLoadingIndicator(false);
                }
            });
        }
    }

    /**
     * Checks if entered city already added or not
     *
     * @param city
     * @return
     */
    private boolean isCityExists(String city) {
        ArrayList<String> addWeatherCities = WeatherPreferences.getInstance(getContext()).getWeatherCities();
        return addWeatherCities.contains(city.toUpperCase());
    }
}

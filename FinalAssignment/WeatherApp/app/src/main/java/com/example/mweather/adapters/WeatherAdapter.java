package com.example.mweather.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mweather.R;
import com.example.mweather.fragment.WeatherFragment;
import com.example.mweather.models.WeatherItem;
import com.example.mweather.utils.EmptyRecyclerView;
import com.example.mweather.utils.WeatherUtil;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Adapter to display each city weather in cardView and scroll data horizontally
 */
public class WeatherAdapter extends EmptyRecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<WeatherItem> mWeatherItems;
    private Resources mResources;
    private String mPackageName;
    private WeatherFragment.WeatherListener mWeatherListener;

    public WeatherAdapter(@NonNull Context context, @NonNull ArrayList<WeatherItem> weatherItems,
                          WeatherFragment.WeatherListener weatherListener) {
        checkNotNull(context);
        mWeatherItems = weatherItems;
        mResources = context.getResources();
        mPackageName = context.getPackageName();
        mWeatherListener = weatherListener;
    }

    /**
     * Replaces adapter with new one and notifies RecyclerView
     */
    public void swap(ArrayList<WeatherItem> weatherItems) {
        mWeatherItems = weatherItems;
        notifyDataSetChanged();
    }

    /**
     * View holder to hold view for each of
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIVWeatherIcon;
        private TextView mTVDegreeCelsius;
        private TextView mTVHumidity;
        private TextView mTVWind;
        private TextView mTVRainLevel;
        private TextView mTVCity;
        private ImageView mIVTrash;
        private TextView mTVDiscription;

        private ViewHolder(View view) {
            super(view);
            mIVWeatherIcon = view.findViewById(R.id.iv_weather_icon);
            mTVDegreeCelsius = view.findViewById(R.id.tv_degree);
            mTVHumidity = view.findViewById(R.id.tv_humidity);
            mTVWind = view.findViewById(R.id.tv_wind);
            mTVRainLevel = view.findViewById(R.id.tv_rain_level);
            mTVCity = view.findViewById(R.id.tv_city_name);
            mIVTrash = view.findViewById(R.id.iv_trash);
            mTVDiscription = view.findViewById(R.id.tv_discription_weather);
        }
    }


    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                        int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_weather_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mTVWind.setText(getItem(position).getWind() + "m/s");
        holder.mTVDegreeCelsius.setText(getItem(position).getTemperature());
        holder.mTVHumidity.setText(getItem(position).getHumidity() + "%");
        holder.mTVRainLevel.setText(String.valueOf(getItem(position).getRainLevel()));
        holder.mIVWeatherIcon.setImageDrawable(getDrawable(WeatherUtil
                        .getWeatherIcon(getItem(position).getImg())
                , mResources, mPackageName));
        holder.mTVCity.setText(String.valueOf(getItem(position).getCity()));
        holder.mIVTrash.setOnClickListener(view -> mWeatherListener.onWeatherTrashTouched(holder.getAdapterPosition()));
        holder.mTVDiscription.setText(getItem(position).getdiscription());
    }

    @Override
    public int getItemCount() {
        return mWeatherItems.size();
    }

    /**
     * get the weather item at the position
     *
     * @param position index
     * @return weather item
     */
    private WeatherItem getItem(int position) {
        return mWeatherItems.get(position);
    }

    /**
     * get drawable from provided resource name
     */
    private static Drawable getDrawable(String name, Resources resources, String packageName) {
        final int resourceId = resources.getIdentifier(name, "drawable", packageName);
        return resources.getDrawable(resourceId);
    }

}

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
 * Adapter to display each city weather in cardview and scroll data horizontally
 */
public class WeatherAdapter extends EmptyRecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<WeatherItem> mWeatherItems;
    private Context mContext;
    private Resources mResources;
    private String mPackageName;
    private WeatherFragment.WeatherListener mWeatherListener;

    public WeatherAdapter(@NonNull Context context, @NonNull ArrayList<WeatherItem> weatherItems, WeatherFragment.WeatherListener weatherListener) {
        checkNotNull(context);
        mWeatherItems = weatherItems;
        mContext = context;
        mResources = mContext.getResources();
        mPackageName = mContext.getPackageName();
        mWeatherListener = weatherListener;
    }

    /**
     * Replaces adapter with new one and notifies RecyclerView
     * @param weatherItems
     */
    public void swap(ArrayList<WeatherItem> weatherItems) {
        mWeatherItems = weatherItems;
        notifyDataSetChanged();
    }

    /**
     * View holder to hold view for each of
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIVWeatherIcon;
        public TextView mTVDegreeCelsius;
        public TextView mTVHumidity;
        public TextView mTVWind;
        public TextView mTVRainLevel;
        public TextView mTVCity;
        public ImageView mIVTrash;

        public ViewHolder(View v) {
            super(v);
            mIVWeatherIcon = v.findViewById(R.id.iv_weather_icon);
            mTVDegreeCelsius = v.findViewById(R.id.tv_degree);
            mTVHumidity = v.findViewById(R.id.tv_humidity);
            mTVWind = v.findViewById(R.id.tv_wind);
            mTVRainLevel = v.findViewById(R.id.tv_rain_level);
            mTVCity = v.findViewById(R.id.tv_city_name);
            mIVTrash = v.findViewById(R.id.iv_trash);
        }
    }


    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_weather_v2, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTVWind.setText(getItem(position).getWind() + "m/s");
        //holder.mTVDegreeCelsius.setText(String.valueOf(getItem(position).getDegreeCelsius()) + " \u2103");
        //holder.mTVDegreeCelsius.setText(String.valueOf(getItem(position).getDegreeCelsius()) + " \u2109");
        holder.mTVDegreeCelsius.setText(getItem(position).getTemperature());
        holder.mTVHumidity.setText(getItem(position).getHumidity() + "%");
        holder.mTVRainLevel.setText(String.valueOf(getItem(position).getRainLevel()));
        holder.mIVWeatherIcon.setImageDrawable(getDrawable(WeatherUtil.getWeatherIcon(getItem(position).getImg()), mResources, mPackageName));
        holder.mTVCity.setText(String.valueOf(getItem(position).getCity()));
        holder.mIVTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWeatherListener.onWeatherTrashTouched(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWeatherItems.size();
    }

    /**
     * get the weather item at the position
     * @param position
     * @return
     */
    public WeatherItem getItem(int position) {
        return mWeatherItems.get(position);
    }

    /**
     * get drawable from provided resource name
     * @param name
     * @param resources
     * @param packageName
     * @return
     */
    public static Drawable getDrawable(String name, Resources resources, String packageName) {
        final int resourceId = resources.getIdentifier(name, "drawable", packageName);
        return resources.getDrawable(resourceId);
    }

}

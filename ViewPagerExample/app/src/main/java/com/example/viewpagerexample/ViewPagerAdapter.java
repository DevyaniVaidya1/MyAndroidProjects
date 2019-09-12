package com.example.viewpagerexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

public class ViewPagerAdapter extends PagerAdapter {
    Context mContext;
    private String[] imageUrls;

    public ViewPagerAdapter(Context mContext, String[] imageUrls) {
        this.mContext = mContext;
        this.imageUrls = imageUrls;
    }

    /**
     * It returns the number of available views in ViewPager.
     * @return count
     */
    @Override
    public int getCount() {
        return imageUrls.length;
    }

    /**
     *  This method checks the view whether it is associated with key and returned by instantiateItem().
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    /**
     * This method creates the page position passed as an argument.
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


       ImageView imageView = new ImageView(mContext);

        Picasso.get()
                .load(imageUrls[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    /**
     * It removes the page from its current position from container.
     * In this example we simply removed object using removeView().
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.example.slidingtabsusingviewpager.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.slidingtabsusingviewpager.fragment.Tab1;
import com.example.slidingtabsusingviewpager.fragment.Tab2;
import com.example.slidingtabsusingviewpager.fragment.Tab3;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fragmentManager, int NumberOfTabs)
    {
        super(fragmentManager);
        this.mNoOfTabs = NumberOfTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return  tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return  tab3;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}

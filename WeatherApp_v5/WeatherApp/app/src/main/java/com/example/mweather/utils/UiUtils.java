package com.example.mweather.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.google.common.base.Preconditions.checkNotNull;

public class UiUtils {

    /**
     * Add the fragment to activity
     * @param fragmentManager
     * @param fragment
     * @param frameId
     * @param isAddToBackstack
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean isAddToBackstack) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        if (isAddToBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    /**
     * replace the current fragment from activity
     * @param fragmentManager
     * @param fragment
     * @param frameId
     * @param isAddToBackstack
     */
    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean isAddToBackstack) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        if (isAddToBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}

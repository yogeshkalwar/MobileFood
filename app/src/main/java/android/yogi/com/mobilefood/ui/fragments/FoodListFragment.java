package android.yogi.com.mobilefood.ui.fragments;

import android.os.Bundle;

/**
 * Created by yogesh.kalwar on 29/09/2016.
 */
public class FoodListFragment extends TabFragment {
    public static final String TAG = FoodListFragment.class.getSimpleName();

    public static final FoodListFragment getInstance(final String title){
        final FoodListFragment fragment =  new FoodListFragment();
        final Bundle args = TabFragment.getBundle(title);
        fragment._sTitle = title;
        fragment.setArguments(args);
        return fragment;
    }
}

package android.yogi.com.mobilefood.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.yogi.com.mobilefood.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yogesh.kalwar on 28/09/2016.
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();


    private TabLayout _oTabLayout;
    private ViewPager _oViewPager;

    public static final HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        _oTabLayout = (TabLayout)view.findViewById(R.id.fragment_home_tab_layout);
        _oViewPager = (ViewPager)view.findViewById(R.id.fragment_home_pager);
        init();
        return view;
    }

    private void init(){
        final PagerAdapter adapter  = new PagerAdapter(getFragmentManager());
        adapter.addFragment(MapFragment.getInstance(getResources().getString(R.string.navigation_drawer_tab_map)));
        adapter.addFragment(FoodListFragment.getInstance(getResources().getString(R.string.navigation_drawer_tab_list)));

        _oViewPager.setAdapter(adapter);

        _oTabLayout.setupWithViewPager(_oViewPager);
    }

    public static class PagerAdapter extends FragmentStatePagerAdapter{
        final List<TabFragment> _arrFragments = new ArrayList<TabFragment>();

        public PagerAdapter(FragmentManager manager){
            super(manager);
        }

        public void addFragment(TabFragment fragment){
            _arrFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return _arrFragments.get(position);
        }

        @Override
        public int getCount() {
            return _arrFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position){
            return _arrFragments.get(position).getTitle();
        }

    }
}

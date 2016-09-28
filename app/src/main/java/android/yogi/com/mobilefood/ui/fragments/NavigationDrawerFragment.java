package android.yogi.com.mobilefood.ui.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.yogi.com.mobilefood.R;
import android.yogi.com.mobilefood.constant.Constants;
import android.yogi.com.mobilefood.interfaces.DrawerSelectionCallback;

/**
 * Created by yogesh.kalwar on 27/09/2016.
 */
public class NavigationDrawerFragment extends BaseFragment{

    private static final String ARG_SAVED_SELECTED_POSITION = "saved_selected_position";
    private ActionBarDrawerToggle _oActionBarDrawerToggle;
    private DrawerSelectionCallback _oNavigationDrawerListener;
    private DrawerLayout _oDrawerLayout;
    //Menu items
    private TextView _tvClose;
    private TextView _tvHome;
    private TextView _tvAboutUs;
    private View _oDrawerContainerFragment;
    //others
    private Constants.NavigationAction _eCurrentSelectedAction = Constants.NavigationAction.HOME;
    private int _iCurrentItemSelected = Constants.NavigationAction.HOME.ordinal();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            this._iCurrentItemSelected = savedInstanceState.getInt(ARG_SAVED_SELECTED_POSITION, Constants.NavigationAction.HOME.ordinal());
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        onAction(_eCurrentSelectedAction);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_nav_drawer, null, false);
        _tvClose = (TextView)view.findViewById(R.id.fragment_navigation_drawer_close);
        _tvHome = (TextView)view.findViewById(R.id.fragment_navigation_drawer_home);
        _tvAboutUs = (TextView)view.findViewById(R.id.fragment_navigation_drawer_about_us);
        init();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _oNavigationDrawerListener = (DrawerSelectionCallback)context;
    }

    @Override
    public void onDetach() {
        _oNavigationDrawerListener = null;
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_SAVED_SELECTED_POSITION, _iCurrentItemSelected);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        _oActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (_oActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void disable(){
        _oActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        _oDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void enable(){
        _oActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        _oDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public boolean isEnabled(){
        return _oActionBarDrawerToggle.isDrawerIndicatorEnabled();
    }

    private void init(){
        setupMenu();
    }

    private void setupMenu(){
        _tvClose.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                _oDrawerLayout.closeDrawer(_oDrawerContainerFragment);
            }
        });
        _tvHome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onAction(Constants.NavigationAction.HOME);
            }
        });
        _tvAboutUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onAction(Constants.NavigationAction.ABOUT_US);
            }
        });
    }

    public void initWithDrawerLayout(final View fragmentContainer, final DrawerLayout drawerLayout, final Toolbar toolbar){
        _oDrawerContainerFragment = fragmentContainer;
        _oDrawerLayout = drawerLayout;
        _oActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), _oDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()){
                    return;
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()){
                    return;
                }
                getActivity().supportInvalidateOptionsMenu();
            }
        };
        _oDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                _oActionBarDrawerToggle.syncState();
            }
        });
        _oDrawerLayout.setDrawerListener(_oActionBarDrawerToggle);
        _oActionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    public boolean isDrawerOpen(){
        return _oDrawerLayout != null && _oDrawerLayout.isDrawerOpen(_oDrawerContainerFragment);
    }

    public void onAction(final Constants.NavigationAction action){
        _eCurrentSelectedAction = action;
        _iCurrentItemSelected = action.ordinal();
        //close the drawer
        if (_oDrawerLayout != null){
            _oDrawerLayout.closeDrawer(_oDrawerContainerFragment);
        }
        //excute the action
        if (_oNavigationDrawerListener != null){
            _oNavigationDrawerListener.OnItemSelected(action);
        }
    }
}

package android.yogi.com.mobilefood.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.yogi.com.mobilefood.R;
import android.yogi.com.mobilefood.constant.Constants;
import android.yogi.com.mobilefood.interfaces.BaseController;
import android.yogi.com.mobilefood.interfaces.Controller;
import android.yogi.com.mobilefood.interfaces.DrawerSelectionCallback;
import android.yogi.com.mobilefood.interfaces.OnBackPressedListener;
import android.yogi.com.mobilefood.ui.fragments.AboutUsFragment;
import android.yogi.com.mobilefood.ui.fragments.HomeFragment;
import android.yogi.com.mobilefood.ui.fragments.NavigationDrawerFragment;

public class NavigationDrawerActivity extends BaseActivity
        implements DrawerSelectionCallback, Controller {

    //Child drawer fragment
    private NavigationDrawerFragment _oDrawerFragment;

    public static void showMe(final Context context){
        context.startActivity(new Intent(context, NavigationDrawerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _iContainerId = R.id.fragment_container;
        setContentView(LayoutInflater.from(this).inflate(R.layout.activity_nav_drawer, null, false));
        init();
    }

    private void init(){
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.yogi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _oDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_nav_drawer);
        _oDrawerFragment.initWithDrawerLayout(findViewById(R.id.fragment_nav_drawer), (DrawerLayout) findViewById(R.id.nav_drawer_drawer_layout), toolbar);
    }

    @Override
    public void onBackPressed() {
        processOnBackPressed(false);
    }

    private void processOnBackPressed(boolean isHomeBackPressed){
        final Fragment current = getCurrentFragment();
        if (current != null && current instanceof OnBackPressedListener){
            if (((OnBackPressedListener)current).onBackPressed(isHomeBackPressed)){
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemSelected(Constants.NavigationAction action) {
        switch(action){
            case HOME:
                showFragment(HomeFragment.getInstance(), HomeFragment.TAG, false);
                break;
            case ABOUT_US:
                showFragment(AboutUsFragment.getInstance(), AboutUsFragment.TAG, false);
                break;
        }
    }
}

package android.yogi.com.mobilefood.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.yogi.com.mobilefood.interfaces.BaseController;
import android.yogi.com.mobilefood.ui.fragments.BaseFragment;

/**
 * Created by yogesh.kalwar on 27/09/2016.
 */
public class BaseActivity extends AppCompatActivity implements BaseController{
    protected int _iContainerId;

    protected Fragment getCurrentFragment(){
        final FragmentManager manager = getSupportFragmentManager();
        return manager.findFragmentById(_iContainerId);
    }

    protected Fragment getFragment(final String tag){
        final FragmentManager manager = getSupportFragmentManager();
        return manager.findFragmentByTag(tag);
    }

    public void showFragment(final Fragment fragment, final String tag, final boolean addToBackStacks){
        //TODO: hide the soft keyboard
        final FragmentManager manager = getSupportFragmentManager();
        final Fragment current = getCurrentFragment();
        if (current == null || !current.getClass().equals(fragment.getClass())){
            final FragmentTransaction transaction = manager.beginTransaction();
            if (fragment instanceof BaseFragment){
                ((BaseFragment)fragment).getAnimation().setAnimationValues(transaction);
            }
            transaction.replace(_iContainerId, fragment, tag);
            if (addToBackStacks){
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }
}

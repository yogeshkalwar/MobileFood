package android.yogi.com.mobilefood.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.yogi.com.mobilefood.animations.BaseAnimation;
import android.yogi.com.mobilefood.interfaces.BaseController;
import android.yogi.com.mobilefood.interfaces.Controller;

/**
 * Created by yogesh.kalwar on 27/09/2016.
 */
public class BaseFragment extends Fragment {
    private BaseAnimation _customAnimation = BaseAnimation.DEFAULT;

    private BaseController _oBaseController;

    public BaseController getBaseController(){
        return _oBaseController;
    }

    public Controller getController(){
        return null;
    }

    public BaseAnimation getAnimation(){
        return _customAnimation;
    }

    protected void setAnimation(final BaseAnimation animation){
        this._customAnimation = new BaseAnimation(animation);
    }
}

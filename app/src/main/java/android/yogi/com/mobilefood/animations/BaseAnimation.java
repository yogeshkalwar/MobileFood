package android.yogi.com.mobilefood.animations;

import android.support.v4.app.FragmentTransaction;

/**
 * Created by yogesh.kalwar on 27/09/2016.
 */
public class BaseAnimation {
    private int _iEnter;
    private int _iExit;
    private int _iPopEnter;
    private int _iPopExit;

    public static final BaseAnimation DEFAULT = new BaseAnimation(0 , 0, 0, 0);

    public BaseAnimation(int enter, int exit, int popEnter, int popExit){
        this._iEnter = enter;
        this._iExit = exit;
        this._iPopEnter = popEnter;
        this._iPopExit = popExit;
    }

    public BaseAnimation(){
        this(DEFAULT);
    }

    public BaseAnimation(final BaseAnimation other){
        _iEnter = other._iEnter;
        _iExit = other._iExit;
        _iPopEnter = other._iPopEnter;
        _iPopExit = other._iPopExit;
    }

    public void setAnimationValues(final FragmentTransaction transaction){
        transaction.setCustomAnimations(_iEnter, _iExit, _iPopEnter, _iPopExit);
    }

    public int getEnterValue(){
        return _iEnter;
    }

    public int getExitValue(){
        return _iExit;
    }

    public int getPopEnterValue(){
        return _iPopEnter;
    }

    public int getPopExitValue(){
        return _iPopExit;
    }
}

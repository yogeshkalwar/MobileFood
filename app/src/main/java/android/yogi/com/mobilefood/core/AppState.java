package android.yogi.com.mobilefood.core;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yogesh.kalwar on 03/10/2016.
 */
public class AppState {
    private static AppState ourInstance = new AppState();

    public static AppState getInstance() {
        return ourInstance;
    }

    private static final int DEFAULT_FETCH_COUNT = 10;
    private static final int MULTIPLIER_WIFI = 4;
    private static final int MULTIPLIER_4G = 3;
    private static final int MULTIPLIER_3G = 2;

    private boolean _bSanityCheckDone;
    private boolean _bIsNetworkConnected;
    private NetworkType _eNetworkType;

    public static enum NetworkType{
        WIFI,
        MOBILE_4G,
        MOBILE_3G,
        METERED;
        private NetworkType(){

        }
    }

    private AppState() {
        _bSanityCheckDone = false;
        _bIsNetworkConnected = false;
        _eNetworkType = NetworkType.METERED;
    }

    public void clean(){

    }

    public void setSanityCheck(){
        synchronized (this){
            _bSanityCheckDone = true;
        }
    }

    public boolean isSanityCheckDone(){
        return _bSanityCheckDone;
    }

    public void setNetworkConnectionState(boolean state){
        if (_bIsNetworkConnected == state){
            return;
        }
        synchronized (this) {
            _bIsNetworkConnected = state;
            //fire network change state

            Log.d("Check","Network connected : "+_bIsNetworkConnected+ " fetch = "+getDownloadFetchCount());
        }
    }

    public boolean getNetworkConnectionState(){
        return _bIsNetworkConnected;
    }

    public void setNetworkType(final NetworkType type){
        synchronized (this){
            _eNetworkType = type;
        }
    }

    public int getDownloadFetchCount(){
        int count  = DEFAULT_FETCH_COUNT;
        switch(_eNetworkType){
            case WIFI:
                count *= MULTIPLIER_WIFI;
                break;
            case MOBILE_4G:
                count *= MULTIPLIER_4G;
                break;
            case MOBILE_3G:
                count *= MULTIPLIER_3G;
                break;
        }
        return count;
    }
}

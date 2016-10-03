package android.yogi.com.mobilefood.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.yogi.com.mobilefood.core.AppState;

import java.lang.ref.WeakReference;

/**
 * Created by yogesh.kalwar on 03/10/2016.
 */

public class NetworkStateSensor implements Runnable{

    private WeakReference<Context> _oContext = null;

    public NetworkStateSensor(final Context context){
        _oContext = new WeakReference<Context>(context);
    }

    private void process(){
        final Context context = _oContext != null? _oContext.get() : null;
        if (context != null){
            final ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null){
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (cm.isActiveNetworkMetered()){
                    AppState.getInstance().setNetworkType(AppState.NetworkType.METERED);
                }else {
                    switch (activeNetwork.getType()) {
                        case ConnectivityManager.TYPE_WIFI:
                            AppState.getInstance().setNetworkType(AppState.NetworkType.WIFI);
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                            if (is4GNetwork(activeNetwork.getSubtype())) {
                                AppState.getInstance().setNetworkType(AppState.NetworkType.MOBILE_4G);
                            } else {
                                AppState.getInstance().setNetworkType(AppState.NetworkType.MOBILE_3G);
                            }
                            break;
                    }
                }
                //Now update network connection state
                AppState.getInstance().setNetworkConnectionState(isConnected);
            }
        }
    }

    private boolean is4GNetwork(int subtype){
        boolean is4GNetwork = false;
        switch (subtype){
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                is4GNetwork = true;
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                is4GNetwork = true;
                break;
        }
        return is4GNetwork;
    }

    @Override
    public void run() {
        process();
    }
}

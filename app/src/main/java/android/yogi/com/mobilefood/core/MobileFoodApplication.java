package android.yogi.com.mobilefood.core;

import android.app.Application;
import android.widget.Toast;
import android.yogi.com.mobilefood.network.NetworkStateSensor;

/**
 * Created by yogesh.kalwar on 27/09/2016.
 */
public class MobileFoodApplication extends Application {

    private Thread _oThreadNetworkStateSensor;

    @Override
    public void onCreate() {
        super.onCreate();

        initApp();
    }

    @Override
    public void onTerminate() {
        cleanApp();

        super.onTerminate();
    }

    private void initApp(){
        //TODO: reset network to 3G and start network state synce
        (_oThreadNetworkStateSensor = new Thread(new NetworkStateSensor(this))).start();
    }

    private void cleanApp(){
        AppState.getInstance().clean();
    }

    public void runNetworkStateSensor(boolean bBruteForce){
        if (_oThreadNetworkStateSensor != null){
            if (_oThreadNetworkStateSensor.isAlive()){
                if (bBruteForce){
                    try{
                        _oThreadNetworkStateSensor.interrupt();
                    }catch (RuntimeException e){
                        e.printStackTrace();
                    }
                }else{
                    return;
                }
            }
        }
        (_oThreadNetworkStateSensor = new Thread(new NetworkStateSensor(this))).start();
    }
}

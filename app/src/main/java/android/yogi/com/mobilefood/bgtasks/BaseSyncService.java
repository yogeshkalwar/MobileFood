package android.yogi.com.mobilefood.bgtasks;

import android.yogi.com.mobilefood.interfaces.SyncListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yogesh.kalwar on 03/10/2016.
 */

public abstract class BaseSyncService {
    private final List<SyncListener> _arrSyncListeners = new ArrayList<SyncListener>();

    /***/
    public void addListener(final SyncListener listener){
        _arrSyncListeners.add(listener);
    }

    /***/
    public void removeListener(final SyncListener listener){
        _arrSyncListeners.remove(listener);
    }

    /***/
    private void updateSyncComplete(){
        for (final SyncListener listener : _arrSyncListeners){
            listener.onSyncCompleted();
        }
    }

    /***/
    private void updateSyncFailure(){
        for (final SyncListener listener : _arrSyncListeners){
            listener.onSyncFailed();
        }
    }

    /***/
    private void updateSyncCancel(){
        for (final SyncListener listener : _arrSyncListeners){
            listener.onSyncCancelled();
        }
    }
}

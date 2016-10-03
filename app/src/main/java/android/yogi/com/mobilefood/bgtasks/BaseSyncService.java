package android.yogi.com.mobilefood.bgtasks;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.yogi.com.mobilefood.interfaces.SyncListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yogesh.kalwar on 03/10/2016.
 */

public abstract class BaseSyncService implements Runnable{

    public static enum SyncMode{
        IN_BATCH;
        private SyncMode(){
        }
    }

    protected static final String ARG_SYNC_MODE = "sync_service_mode";
    protected static final String ARG_SYNC_REQ_ID = "sync_service_req_id";

    private final List<SyncListener> _arrSyncListeners = new ArrayList<SyncListener>();

    protected final Bundle _oRequestData;

    public BaseSyncService(final Bundle bundle, SyncListener listener){
        _oRequestData = bundle;
        addListener(listener);
    }

    @Override
    public void run() {
        if (_oRequestData == null){
            throw new IllegalArgumentException("Request parameters not provided");
        }

        int iReqId = _oRequestData.getInt(ARG_SYNC_REQ_ID, -1);
        processRequest();
        //update complete status to caller if its requested, -1 means no listening
        if (iReqId > -1) {
            updateSyncComplete(iReqId);
        }
    }

    abstract void processRequest();

    /***/
    public void addListener(final SyncListener listener){
        _arrSyncListeners.add(listener);
    }

    /***/
    public void removeListener(final SyncListener listener){
        _arrSyncListeners.remove(listener);
    }

    /***/
    private void updateSyncComplete(final int id){
        for (final SyncListener listener : _arrSyncListeners){
            if (listener != null && listener.isMyRequest(id)) {
                listener.onSyncCompleted();
            }
        }
    }

    /***/
    private void updateSyncFailure(final int id){
        for (final SyncListener listener : _arrSyncListeners){
            if (listener != null && listener.isMyRequest(id)) {
                listener.onSyncFailed();
            }
        }
    }

    /***/
    private void updateSyncCancel(final int id){
        for (final SyncListener listener : _arrSyncListeners){
            if (listener != null && listener.isMyRequest(id)) {
                listener.onSyncCancelled();
            }
        }
    }
}

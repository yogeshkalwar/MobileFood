package android.yogi.com.mobilefood.bgtasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.yogi.com.mobilefood.interfaces.SyncListener;

/**
 * Created by yogesh.kalwar on 03/10/2016.
 */

public class SyncService extends BaseSyncService{

    private static final String ARG_SYNC_PAGE_NO = "sync_service_page_no";

    public static SyncService startBatchSync(final Context context, int page, int id, SyncListener listener){
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_SYNC_MODE, SyncMode.IN_BATCH.ordinal());
        bundle.putInt(ARG_SYNC_PAGE_NO, page);
        bundle.putInt(ARG_SYNC_REQ_ID, id);

        return new SyncService(bundle, listener);
    }

    private SyncService(final Bundle bundle, final SyncListener listener) {
        super(bundle, listener);
    }

    @Override
    void processRequest() {

    }
}

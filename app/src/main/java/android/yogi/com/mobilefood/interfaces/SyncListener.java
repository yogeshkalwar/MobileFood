package android.yogi.com.mobilefood.interfaces;

/**
 * Created by yogesh.kalwar on 03/10/2016.
 */

public interface SyncListener {
    public void onSyncCompleted();
    public void onSyncFailed();
    public void onSyncCancelled();
    public boolean isMyRequest(int id);
}

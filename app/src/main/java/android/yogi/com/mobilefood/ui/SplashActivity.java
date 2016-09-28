package android.yogi.com.mobilefood.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.yogi.com.mobilefood.R;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity {

    private static final int SLEEP_INTERVAL = 1000;
    private AppLoadTask _oAppLoadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        _oAppLoadTask = (AppLoadTask)new AppLoadTask(this).execute();
    }

    @Override
    protected void onDestroy() {
        if (_oAppLoadTask != null && !_oAppLoadTask.isCancelled()){
            _oAppLoadTask.cancel(true);
        }
        _oAppLoadTask = null;
        super.onDestroy();
    }

    private static class AppLoadTask extends AsyncTask<Void, Void, Void> {

        private final WeakReference<SplashActivity> activity;

        public AppLoadTask( SplashActivity activity){
            this.activity = new WeakReference<SplashActivity>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //check version no
            //check other details
            try{
                Thread.sleep(SLEEP_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!isCancelled()){
                //process ui related task

                //load Home
                if (activity.get() != null) {
                    NavigationDrawerActivity.showMe(activity.get());
                }
            }
        }
    }
}

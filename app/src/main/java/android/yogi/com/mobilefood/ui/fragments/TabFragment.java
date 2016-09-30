package android.yogi.com.mobilefood.ui.fragments;

import android.os.Bundle;

/**
 * Created by yogesh.kalwar on 29/09/2016.
 */
public class TabFragment extends BaseFragment {
    private static final String ARG_TITLE = "tab_fragment_args_title";

    //Non ui related info. Just to use for tab
    protected String _sTitle;

    public static final Bundle getBundle(final String title){
        final Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        return bundle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _sTitle = getArguments().getString(ARG_TITLE, getClass().getSimpleName());
    }

    public String getTitle(){
        return _sTitle;
    }

    public void setTitle(final String title){
        this._sTitle = title;
    }
}

package android.yogi.com.mobilefood.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.yogi.com.mobilefood.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by yogesh.kalwar on 29/09/2016.
 */
public class MapFragment extends TabFragment implements OnMapReadyCallback{
    public static final String TAG = MapFragment.class.getSimpleName();

    private GoogleMap _oMap;

    public static final MapFragment getInstance(final String title){
        final MapFragment fragment =  new MapFragment();
        final Bundle args = TabFragment.getBundle(title);
        fragment._sTitle = title;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_map, container, false);
        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _oMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        _oMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        _oMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

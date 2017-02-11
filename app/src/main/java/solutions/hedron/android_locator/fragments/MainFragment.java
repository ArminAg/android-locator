package solutions.hedron.android_locator.fragments;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import solutions.hedron.android_locator.R;
import solutions.hedron.android_locator.models.MyLocation;
import solutions.hedron.android_locator.services.DataService;

public class MainFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions userMarker;
    private LocationsListFragment locationsListFragment;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationsListFragment = (LocationsListFragment)getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.container_locations_list);

        if (locationsListFragment == null){
            locationsListFragment = LocationsListFragment.newInstance();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_locations_list, locationsListFragment)
                    .commit();

        }

        final EditText zipText = (EditText)view.findViewById(R.id.zipText);
        zipText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    // Validate Zip Code - Check total count and characters
                    String text = zipText.getText().toString();
                    int zip = Integer.parseInt(text);

                    InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(zipText.getWindowToken(), 0);
                    showList();
                    updateMapForZip(zip);
                    return true;
                }

                return false;
            }
        });
        hideList();
        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void setUserMarkers(LatLng latLng){
        if (userMarker == null){
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            userMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_icon));
            mMap.addMarker(userMarker);
        }
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            int zip = Integer.parseInt(addresses.get(0).getPostalCode());

            updateMapForZip(zip);
        } catch (IOException exception){

        }

        updateMapForZip(1007);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private void updateMapForZip(int zipCode){
        ArrayList<MyLocation> locations = DataService.getInstance().getLocationsWithin10MilesOfZip(zipCode);

        for (int x = 0; x < locations.size(); x++){
            MyLocation location = locations.get(x);
            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()));
            markerOptions.title(location.getLocationTitle());
            markerOptions.snippet(location.getLocationAddress());
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_icon));
            mMap.addMarker(markerOptions);
        }
    }

    private void hideList(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .hide(locationsListFragment)
                .commit();
    }

    private void showList(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .show(locationsListFragment)
                .commit();
    }
}

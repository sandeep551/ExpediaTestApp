package com.seattle.expedia_test_app.view.map_pins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.seattle.expedia_test_app.R;
import com.seattle.expedia_test_app.injection.component.DaggerMapPinsComponent;
import com.seattle.expedia_test_app.injection.module.MapPinsModule;
import com.seattle.expedia_test_app.model.Venue;
import com.seattle.expedia_test_app.utils.GeoUtils;
import com.seattle.expedia_test_app.view.base.BaseActivity;
import com.seattle.expedia_test_app.view.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class MapPinsActivity extends BaseActivity implements MapPinsView, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    public static final String PLACES = "PLACES_LIST";

    List<Venue> mPlaces;

    private GoogleMap mMap;
    private Map<String, Venue> placesMap;

    @Inject
    protected MapPinsPresenter mPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_map_pins;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerMapPinsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mapPinsModule(new MapPinsModule(this))
                .build().inject(this);
    }

    @Override
    protected void onActivityReady(Bundle savedInstanceState, Intent intent) {

        //get places from PlacesActivity
        mPlaces = intent.getParcelableArrayListExtra(PLACES);

        //prevent NullPointerException
        if (mPlaces != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Changing the map style to : Aubergine
        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.map_aubergine));

        if (!success) {
            onShowToast("Style parsing failed.");
        }

        //Add markers to the map and get a Map object to identify the list
        List<LatLng> listLocations = new ArrayList<>();
        placesMap = mPresenter.addMarkers(mMap,mPlaces,listLocations);

        // Get bounds from existing markers
        LatLngBounds bounds = GeoUtils.getBoundsFromLocations(listLocations);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        // offset from edges of the map 20% of the collapsing toolbar
        int padding = (int) (width * 0.2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

        //Click listener on the marker text
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Fired whenever a marker title is clicked
    @Override
    public void onInfoWindowClick(Marker marker) {
        Venue place = placesMap.get(marker.getId());
        Intent intent = new Intent(MapPinsActivity.this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.PLACE, place);
        startActivity(intent);
    }

    @Override
    public void onShowDialog(String message) {

    }

    @Override
    public void onHideDialog() {

    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

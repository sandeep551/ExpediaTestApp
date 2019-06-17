package com.seattle.expedia_test_app.view.map_pins;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.seattle.expedia_test_app.R;
import com.seattle.expedia_test_app.model.Venue;
import com.seattle.expedia_test_app.utils.GeoUtils;
import com.seattle.expedia_test_app.view.base.BasePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class MapPinsPresenter extends BasePresenter<MapPinsView> {

    private Context mContext;

    @Inject
    public MapPinsPresenter(Context context) {
        mContext = context;
    }

    public Map<String, Venue> addMarkers(GoogleMap mMap, List<Venue> mPlaces, List<LatLng> listLocations) {
        //Map to identify every venue: for click listener ( Map<markerId,place> )
        Map<String, Venue> placesMap = new HashMap<>();

        //Add all markers for each venue
        for (Venue place : mPlaces) {
            LatLng locationVenue = new LatLng(
                    place.getLocation().getLat(), place.getLocation().getLng()
            );
            listLocations.add(locationVenue);
            Marker marker = GeoUtils.addLatLngToMap(
                    mContext, mMap, locationVenue,
                    place.getName(), R.drawable.ic_marker
            );
            placesMap.put(marker.getId(),place);
        }
        return placesMap;
    }
}

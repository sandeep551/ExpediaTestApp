package com.seattle.expedia_test_app.view.places;

import com.seattle.expedia_test_app.model.Venue;
import com.seattle.expedia_test_app.view.base.BaseView;

import java.util.List;

/**
 * Created By Sandeep Rai on 2019-06-16
 */


public interface PlacesView extends BaseView {

    void onSuggestionsLoaded(List<String> suggestions);

    void onClearSuggestions();

    void onPlacesLoaded(List<Venue> venueList);

    void onClearPlaces();

    void onHidePinFab();

    void onShowPinFab();
}

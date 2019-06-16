package com.seattle.expedia_test_app.view.details;

import com.seattle.expedia_test_app.model.Venue;
import com.seattle.expedia_test_app.view.base.BaseView;



public interface DetailsView extends BaseView {
    void setFavoriteImageChecked();
    void setFavoriteImageUnChecked();
    void setVenueDetails(Venue venue);
}

package com.seattle.expedia_test_app.view.details;

import com.seattle.expedia_test_app.model.Venue;
import com.seattle.expedia_test_app.view.base.BaseView;

/**
 * Created By Sandeep Rai on 2019-06-16
 */


public interface DetailsView extends BaseView {
    void setFavoriteImageChecked();
    void setFavoriteImageUnChecked();
    void setVenueDetails(Venue venue);
}

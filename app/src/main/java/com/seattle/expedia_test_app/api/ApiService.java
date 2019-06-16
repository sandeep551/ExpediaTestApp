package com.seattle.expedia_test_app.api;

import com.seattle.expedia_test_app.model.MiniVenuesResponse;
import com.seattle.expedia_test_app.model.VenueDetailsResponse;
import com.seattle.expedia_test_app.model.VenuesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created By Sandeep Rai on 2019-06-16
 */
public interface ApiService {

    //Getting suggestions for Autocomplete search
    @GET("/v2/venues/suggestcompletion")
    io.reactivex.Observable<MiniVenuesResponse> getSuggestions(@Query("limit") int limit,
                                                  @Query("query") String query);

    //Getting Places
    @GET("/v2/venues/search")
    Observable<VenuesResponse> getPlaces(@Query("limit") int limit,
                                         @Query("query") String query);

    //Getting Venue details
    @GET("/v2/venues/{venueid}")
    io.reactivex.Observable<VenueDetailsResponse> getVenueDetails(@Path("venueid") String id);

}

package com.seattle.expedia_test_app.view.details;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.seattle.expedia_test_app.api.ApiService;
import com.seattle.expedia_test_app.model.Venue;
import com.seattle.expedia_test_app.model.VenueDetailsResponse;
import com.seattle.expedia_test_app.view.base.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class DetailsPresenter extends BasePresenter<DetailsView> implements Observer<Venue> {

    private static final String LIST_FAV_IDS = "LIST_FAV_IDS";
    private static final String TAG = DetailsPresenter.class.getSimpleName();

    private List<String> favIds;

    private SharedPreferences mSharedPreferences;

    @Inject
    protected ApiService mApiService;
    private Observable<VenueDetailsResponse> mDetailsObservable;

    @Inject
    public DetailsPresenter(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        favIds = new ArrayList<>( getFavPlacesIds() );
    }

    //return a list of ids of favorite places
    public List<String> getFavPlacesIds(){
        String listString = mSharedPreferences.getString(LIST_FAV_IDS, "");
        return Arrays.asList(listString.trim().split(","));
    }

    public void setImageToBtn(String id) {
        if (favIds.contains(id)) {
            getView().setFavoriteImageChecked();
        } else {
            getView().setFavoriteImageUnChecked();
        }
    }

    //Add or remove a place and save changes
    public void handleFavEvent(Venue place) {
        if (favIds.contains(place.getId())) {
            favIds.remove(place.getId());
            getView().setFavoriteImageUnChecked();
        } else {
            favIds.add(place.getId());
            getView().setFavoriteImageChecked();
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_FAV_IDS, TextUtils.join(",", favIds));
        editor.apply();
    }

    //Get Venue details
    public void getVenueDetails(String venueId){
        getView().onShowDialog("Loading....");

        //Create the request
        mDetailsObservable = mApiService.getVenueDetails(venueId); //mApiService.getVenueDetails(venueId);

        //Subscribe and map the results to a list of string
        subscribe(mDetailsObservable, this,
                    response -> Observable.just(response.getResponse().getVenue())
        );
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Venue venue) {
        getView().onHideDialog();

        getView().setVenueDetails(venue);
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        Log.d(TAG, "onError: " + e.getMessage());
        getView().onShowToast("Error loading " + e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}

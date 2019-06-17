package com.seattle.expedia_test_app.view.places;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.seattle.expedia_test_app.api.ApiService;
import com.seattle.expedia_test_app.model.MiniVenue;
import com.seattle.expedia_test_app.model.MiniVenuesResponse;
import com.seattle.expedia_test_app.model.Venue;
import com.seattle.expedia_test_app.model.VenuesResponse;
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

public class PlacesPresenter extends BasePresenter<PlacesView> {

    private static final String TAG = PlacesPresenter.class.getSimpleName() + "TAG";
    private static final String LIST_FAV_IDS = "LIST_FAV_IDS";

    @Inject
    protected ApiService mApiService;

    private SharedPreferences mSharedPreferences;

    private List<String> favIds;

    private Observable<MiniVenuesResponse> mSuggObservable;
    private Observable<VenuesResponse> mPlacesObservable;

    @Inject
    public PlacesPresenter(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
        favIds = new ArrayList<>(getFavPlacesIds());
    }

    //Get suggestions only if there is more than 2 chars and clear the list if not
    public void getSuggestions(String query) {

        if (query.length() > 2) {

            //Cancel the previous call if exists
            if (mSuggObservable != null) {
                mSuggObservable.doOnDispose(() -> {
                });
                mSuggObservable = null;
            }

            //Create the request with 5 maximum suggestions
            mSuggObservable = mApiService.getSuggestions(5, query.trim());

            //Subscribe and map the results to a list of string
            subscribe(mSuggObservable, new SuggestionObserver(),
                    response -> Observable.fromIterable(response.getResponse().getMinivenues())
                            .map(MiniVenue::getName)
                            .toList().toObservable()
            );
        } else {
            getView().onClearSuggestions();
        }
    }

    //Get venues from api
    public void getPlaces(String query) {

        if (query.trim().length() < 3) {
            getView().onShowToast("Not enough...");
            getView().onHidePinFab();
            getView().onClearPlaces();
            return;
        }

        getView().onShowDialog("Loading....");
        //Cancel the previous call if exists
        if (mPlacesObservable != null) {
            mPlacesObservable.doOnDispose(() -> {
            });
            mPlacesObservable = null;
        }

        //Create the request with 15 maximum venues
        mPlacesObservable = mApiService.getPlaces(15, query.trim());

        //Subscribe and map the results to a list of string
        subscribe(mPlacesObservable, new PlacesObserver(),
                response -> Observable.fromIterable(response.getResponse().getVenues())
                        .toList().toObservable()
        );
    }

    //return a list of ids of favorite places
    public List<String> getFavPlacesIds() {
        String listString = mSharedPreferences.getString(LIST_FAV_IDS, "");
        return Arrays.asList(listString.trim().split(","));
    }

    //Add or remove a place and save changes
    public void handleFavEvent(Venue place) {
        if (favIds.contains(place.getId())) {
            favIds.remove(place.getId());
        } else {
            favIds.add(place.getId());
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_FAV_IDS, TextUtils.join(",", favIds));
        editor.apply();
    }

    class SuggestionObserver implements Observer<List<String>> {

        //Display loaded dropdown items if exists, if not show Toast
        @Override
        public void onNext(List<String> suggestions) {
            getView().onClearSuggestions();
            if (suggestions.size() > 0) {
                getView().onSuggestionsLoaded(suggestions);
            } else {
                getView().onShowToast("No results for this search");
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: " + e.getMessage());
            getView().onShowToast("Error loading " + e.getMessage());
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onComplete() {

        }
    }

    class PlacesObserver implements Observer<List<Venue>> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<Venue> venues) {
            getView().onHideDialog();
            getView().onClearPlaces();
            if (venues.size() > 0) {
                getView().onPlacesLoaded(venues);
                getView().onShowPinFab();
            } else {
                getView().onShowToast("No results for this search");
                getView().onHidePinFab();
            }
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
}

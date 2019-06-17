package com.seattle.expedia_test_app.injection.module;

import com.seattle.expedia_test_app.api.ApiService;
import com.seattle.expedia_test_app.injection.scope.PerActivity;
import com.seattle.expedia_test_app.view.places.PlacesView;

import dagger.Module;
import dagger.Provides;

import retrofit2.Retrofit;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

@Module
public class PlacesModule {

    private PlacesView mView;

    public PlacesModule(PlacesView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    PlacesView provideView() {
        return mView;
    }

    @PerActivity
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}

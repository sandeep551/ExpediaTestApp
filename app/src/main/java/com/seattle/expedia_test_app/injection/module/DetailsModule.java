package com.seattle.expedia_test_app.injection.module;

import com.seattle.expedia_test_app.api.ApiService;
import com.seattle.expedia_test_app.injection.scope.PerActivity;
import com.seattle.expedia_test_app.view.details.DetailsView;

import dagger.Module;
import dagger.Provides;

import retrofit2.Retrofit;

@Module
public class DetailsModule {
    private DetailsView mView;

    public DetailsModule(DetailsView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    DetailsView provideView() {
        return mView;
    }

    @PerActivity
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}

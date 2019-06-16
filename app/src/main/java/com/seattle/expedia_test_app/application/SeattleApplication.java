package com.seattle.expedia_test_app.application;

import android.app.Application;

import com.seattle.expedia_test_app.injection.component.ApplicationComponent;
import com.seattle.expedia_test_app.injection.component.DaggerApplicationComponent;
import com.seattle.expedia_test_app.injection.module.ContextModule;
import com.seattle.expedia_test_app.injection.module.NetworkModule;
import com.seattle.expedia_test_app.injection.module.SharedPreferencesModule;



/**
 * Created By Sandeep Rai on 2019-06-16
 */
public class SeattleApplication extends Application {

    private static final String BASE_URL = "https://api.foursquare.com";
    private static final String FILE_NAME = "app.seattle.favorites";

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent

                .builder()
                .contextModule(new ContextModule(this))
                .networkModule(new NetworkModule(BASE_URL))
                .sharedPreferencesModule(new SharedPreferencesModule(FILE_NAME))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

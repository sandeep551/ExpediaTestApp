package com.seattle.expedia_test_app.injection.module;

import com.seattle.expedia_test_app.injection.scope.PerActivity;
import com.seattle.expedia_test_app.view.map_pins.MapPinsView;

import dagger.Module;
import dagger.Provides;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

@Module
public class MapPinsModule {
    private MapPinsView mView;

    public MapPinsModule(MapPinsView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    MapPinsView provideView() {
        return mView;
    }
}

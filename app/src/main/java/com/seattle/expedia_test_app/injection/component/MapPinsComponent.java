package com.seattle.expedia_test_app.injection.component;

import com.seattle.expedia_test_app.injection.module.MapPinsModule;
import com.seattle.expedia_test_app.injection.scope.PerActivity;
import com.seattle.expedia_test_app.view.map_pins.MapPinsActivity;

import dagger.Component;

@PerActivity
@Component(modules = MapPinsModule.class, dependencies = ApplicationComponent.class)
public interface MapPinsComponent {
    void inject(MapPinsActivity activity);
}
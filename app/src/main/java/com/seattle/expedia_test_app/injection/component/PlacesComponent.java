package com.seattle.expedia_test_app.injection.component;

import com.seattle.expedia_test_app.injection.module.PlacesModule;
import com.seattle.expedia_test_app.injection.scope.PerActivity;
import com.seattle.expedia_test_app.view.places.PlacesActivity;

import dagger.Component;

@PerActivity
@Component(modules = PlacesModule.class, dependencies = ApplicationComponent.class)
public interface PlacesComponent {
    void inject(PlacesActivity activity);
}

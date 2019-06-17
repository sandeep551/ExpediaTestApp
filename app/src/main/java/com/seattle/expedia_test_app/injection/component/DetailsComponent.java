package com.seattle.expedia_test_app.injection.component;

import com.seattle.expedia_test_app.injection.module.DetailsModule;
import com.seattle.expedia_test_app.injection.scope.PerActivity;
import com.seattle.expedia_test_app.view.details.DetailsActivity;

import dagger.Component;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

@PerActivity
@Component(modules = DetailsModule.class, dependencies = ApplicationComponent.class)
public interface DetailsComponent {
    void inject(DetailsActivity activity);
}
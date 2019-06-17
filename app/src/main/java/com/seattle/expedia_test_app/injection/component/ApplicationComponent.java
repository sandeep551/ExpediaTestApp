package com.seattle.expedia_test_app.injection.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.seattle.expedia_test_app.injection.module.ContextModule;
import com.seattle.expedia_test_app.injection.module.NetworkModule;
import com.seattle.expedia_test_app.injection.module.SharedPreferencesModule;

import javax.inject.Singleton;

import dagger.Component;

import retrofit2.Retrofit;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, SharedPreferencesModule.class})
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    SharedPreferences exposeSharedPreferences();

    Context exposeContext();
}

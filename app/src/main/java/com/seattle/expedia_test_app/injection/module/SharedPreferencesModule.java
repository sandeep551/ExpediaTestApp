package com.seattle.expedia_test_app.injection.module;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

@Module
public class SharedPreferencesModule {

    private String mFileName;

    public SharedPreferencesModule(String mFileName) {
        this.mFileName = mFileName;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
    }
}

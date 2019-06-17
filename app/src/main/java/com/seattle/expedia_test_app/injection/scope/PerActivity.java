package com.seattle.expedia_test_app.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

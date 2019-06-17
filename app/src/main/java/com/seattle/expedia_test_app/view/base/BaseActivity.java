package com.seattle.expedia_test_app.view.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.seattle.expedia_test_app.application.SeattleApplication;
import com.seattle.expedia_test_app.injection.component.ApplicationComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependency();
        //To be used by child activities
        onActivityReady(savedInstanceState, intent);
    }

    //To put layout resource per activity
    protected abstract int getContentView();

    //Resolving Dagger dependency per activity
    protected abstract void resolveDaggerDependency();

    //called when the child activity is ready
    protected abstract void onActivityReady(Bundle savedInstanceState, Intent intent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unbinder.unbind();
    }

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    protected void showDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((SeattleApplication) getApplication()).getApplicationComponent();
    }

}

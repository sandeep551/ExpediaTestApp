package com.seattle.expedia_test_app.view.base;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

public interface BaseView {
    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);
}

package com.seattle.expedia_test_app.view.base;

public interface BaseView {
    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);
}

package com.xiyoumobile.module.login.presenter.view;

import android.graphics.Bitmap;

import com.xiyoumoblie.lib.common.presenter.view.BaseView;

public interface SmartRoomView extends BaseView {
    void onGetCode(Bitmap bitmap);

    void onGetCodeFail();

    void onLoginFail(String msg);

    void onLoginSucceed(String name);
}

package com.xiyoumobile.xiyouassistant.base.ui;

import android.app.Application;

public class BaseApplication extends Application {
    private static BaseApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }

}

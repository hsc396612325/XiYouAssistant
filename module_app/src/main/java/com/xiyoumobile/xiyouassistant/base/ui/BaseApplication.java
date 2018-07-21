package com.xiyoumobile.xiyouassistant.base.ui;



public class BaseApplication extends com.xiyoumoblie.lib.common.base.BaseApplication {
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

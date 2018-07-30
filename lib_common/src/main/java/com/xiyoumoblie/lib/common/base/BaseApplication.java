package com.xiyoumoblie.lib.common.base;

import android.app.Application;
import android.graphics.Typeface;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xiyoumoblie.lib.common.utils.Utils;

/**
 * Application基类 主Application和组件单独测试使用的Application都继承此基类。
 * 提供了全局的context，Utils.getContext()取得。
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    private Typeface typeface;
    private static BaseApplication instance;


    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //初始化工具类，传递context
        Utils.init(this);

        instance = (BaseApplication) getApplicationContext();
        typeface = Typeface.createFromAsset(instance.getAssets(), "fonts/Adobe-Heiti-Std-R.TTF");//下载的字体

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init( this );
}

    public static  BaseApplication getInstace() {
        return instance;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}

package com.xiyoumoblie.lib.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * 工具类
 * 内容：
 * Application Context的提供
 * 获取View所属Activity
 * 对象判空方法
 * SharedPreferences封装
 */
public class Utils {

    private static Context context;
    private static final String FILENAME = "xiyouassistant";

    private Utils() {}

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * View获取Activity的工具
     *
     * @param view view
     * @return Activity
     */
    public static
    @NonNull
    Activity getActivity(View view) {
        Context context = view.getContext();

        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        throw new IllegalStateException("View " + view + " is not attached to an Activity");
    }

    public static <T> void checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public static void putStringFromPreferences(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringFromPreferences(String key, String defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defValue);
    }

    public static void printPxToDp() {
        int width = 750;//屏幕宽度
        int height = 1334;//屏幕高度
        float screenInch = 4.7f;//屏幕尺寸
        // 设备密度公式
        float density = (float) Math.sqrt(width * width + height * height) / screenInch / 160;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (int px = 0; px <= 1200; px += 1) {
            //像素值除以density
            String dp = (int) (px * 1.0f / density + 0.5) + "";

            stringBuilder.append("<dimen name=\"").append(px + "").append("px\">").append(dp).append("dp</dimen>\n");
        }
        stringBuilder.append("</resources>");
        System.out.println(stringBuilder.toString());
    }

}
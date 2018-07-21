package com.xiyoumobile.xiyouassistant.base.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.xiyoumobile.xiyouassistant.base.ui.BaseApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
//import rx.Observable;
//import rx.functions.Action1;
//import rx.functions.Func1;


public class ReceivedCookiesInterceptor implements Interceptor {
    private static final String TAG = "ReceivedCookiesIntercep";
    public ReceivedCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
//            Observable.from(originalResponse.headers("Set-Cookie")).map(new Func1<String, String>() {
//                @Override
//                public String call(String s) {
//                    String[] cookieArray = s.split(";");
//                    Log.d(TAG, "call: " + cookieArray[0]);
//                    return cookieArray[0];
//                }
//            }).subscribe(new Action1<String>() {
//                @Override
//                public void call(String cookie) {
//                    cookieBuffer.append(cookie).append(";");
//                }
//            });
            SharedPreferences sharedPreferences = BaseApplication.getInstance().getSharedPreferences("cookie", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cookie", cookieBuffer.toString());
            editor.commit();
        }
        return originalResponse;
    }
}

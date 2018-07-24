package com.xiyoumoblie.lib.common.net;

import android.text.TextUtils;
import android.util.Log;

import com.xiyoumoblie.lib.common.utils.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 该类作为OkHttpClient的自定义拦截器使用
 * 目的是在每次请求时自动加入持久化的cookie
 */
public class AddCookiesInterceptor implements Interceptor {

    private static final String TAG = "AddCookiesInterceptor";
    
    AddCookiesInterceptor() {
        super();
    }

    /**
     * 实现interceptor方法，添加cookie头字段
     * @param chain 上一个拦截器传入的拦截器链
     * @return 将当前request传给下一个拦截器，接收后面拦截器处理完毕的response
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        String cookie = Utils.getStringFromPreferences("cookie", "");
        if (!TextUtils.isEmpty(cookie)) {
            Log.d(TAG, "intercept: " + "add cookie:" + cookie);
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}


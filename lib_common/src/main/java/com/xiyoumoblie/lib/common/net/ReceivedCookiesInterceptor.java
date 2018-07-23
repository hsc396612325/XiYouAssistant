package com.xiyoumoblie.lib.common.net;

import android.util.Log;

import com.xiyoumoblie.lib.common.utils.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 该类作为OkHttpClient的自定义拦截器使用
 * 目的是在每次请求完成时取出并持久化cookie
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    private static final String TAG = "ReceivedCookiesIntercep";

    ReceivedCookiesInterceptor() {
        super();
    }

    /**
     * 实现interceptor方法，持久化cookie头字段
     * @param chain 上一个拦截器传入的拦截器链
     * @return 当前response
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        //取出原始response
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            //获取头字段中的cookie
            final StringBuilder cookieBuffer = new StringBuilder();
            List<String> headers = originalResponse.headers("Set-Cookie");
            for (String cookies : headers) {
                String cookie = cookies.split(";")[0];
                cookieBuffer.append(cookie).append(";");
            }

            Log.d(TAG, "intercept: " + "save cookie:" + cookieBuffer.toString());
            //持久化cookie
            Utils.putStringFromPreferences("cookie", cookieBuffer.toString());

        }
        return originalResponse;
    }
}

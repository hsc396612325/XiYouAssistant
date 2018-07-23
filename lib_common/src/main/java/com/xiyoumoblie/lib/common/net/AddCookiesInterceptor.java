package com.xiyoumoblie.lib.common.net;

import android.text.TextUtils;
import android.util.Log;

import com.xiyoumoblie.lib.common.utils.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    private static final String TAG = "AddCookiesInterceptor";
    
    public AddCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        String cookie = Utils.getStringFromPreferences("cookie", "");
        if (TextUtils.isEmpty(cookie)) {
            Log.d(TAG, "intercept: " + "add cookie:" + cookie);
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}


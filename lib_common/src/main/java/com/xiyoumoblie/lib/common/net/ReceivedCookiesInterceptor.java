package com.xiyoumoblie.lib.common.net;

import android.util.Log;

import com.xiyoumoblie.lib.common.utils.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    private static final String TAG = "ReceivedCookiesIntercep";

    public ReceivedCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuilder cookieBuffer = new StringBuilder();
            List<String> headers = originalResponse.headers("Set-Cookie");
            for (String cookies : headers) {
                String cookie = cookies.split(";")[0];
                cookieBuffer.append(cookie).append(";");
            }

            Log.d(TAG, "intercept: " + "add cookie:" + cookieBuffer.toString());
            Utils.putStringFromPreferences("cookie", cookieBuffer.toString());

        }
        return originalResponse;
    }
}

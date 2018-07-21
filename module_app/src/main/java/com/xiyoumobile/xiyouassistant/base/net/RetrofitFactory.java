package com.xiyoumobile.xiyouassistant.base.net;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

import static com.xiyoumobile.xiyouassistant.base.common.Constant.SERVER_ADDRESS;

public class RetrofitFactory {

    public static RetrofitFactory INSTANCE = new RetrofitFactory();
    private static final String TAG = "RetrofitFactory";
    private Retrofit mRetrofit;
    private Interceptor mInterceptor;

    private RetrofitFactory() {
        mInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Host", "127.0.0.1:8080")
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0")
                        .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                        .addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Upgrade-Insecure-Requests", "1")
                        .build();
                return chain.proceed(request);
            }
        };

//        mRetrofit = new Retrofit.Builder()
//                .baseUrl(SERVER_ADDRESS)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(initClient())
//                .build();
    }

    private OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

     public <T> T create(Class<T> service) {
         Log.d(TAG, "create: " + "211111");
        return mRetrofit.create(service);
    }
}

package com.xiyoumobile.module.library.data.source.remote.net;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.xiyoumobile.module.library.data.source.remote.net.LibraryUrls.BASE_URL;

/**
 * Retrofit的封装
 */
public class RetrofitFactory {

    public static RetrofitFactory INSTANCE = new RetrofitFactory();

    private Retrofit mRetrofit;

    private Interceptor mInterceptor;

    private RetrofitFactory() {

        mInterceptor = chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Host", "model.xiyoumobile.com")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            return chain.proceed(request);
        };

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build();
    }

    private OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
    }

    private Interceptor initLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

     public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }


}

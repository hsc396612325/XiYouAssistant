package com.xiyoumoblie.lib.common.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.xiyoumoblie.lib.common.net.NetConstant.SERVER_ADDRESS;

/**
 * Retrofit的封装
 */
public class RetrofitFactory {

    //单例对象
    public static RetrofitFactory  INSTANCE = new RetrofitFactory();

    private Retrofit mRetrofit;
    private Retrofit mSmartRoomLoginRetrofit;

    //添加默认配置的拦截器
    private Interceptor mInterceptor;

    private RetrofitFactory() {
        //自定义通用配置拦截器
        mInterceptor = chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")

                    .addHeader("Connection", "keep-alive")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .build();
            Log.d("Okhttp Url-->",""+ request.url().toString());
            return chain.proceed(request);
        };

        //初始化Retrofit对象
        mRetrofit = new Retrofit.Builder()
                .baseUrl(SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build();

        mSmartRoomLoginRetrofit = new Retrofit.Builder()
                .baseUrl(SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(mInterceptor)
//                        .addInterceptor(new ReceivedCookiesInterceptor())
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .build())
                .build();
    }

    /**
     * 定义OkHttpClient对象，主要是添加拦截器和设置超时时间
     * @return OkHttpClient对象
     */
    private OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 对外暴露的create()，作用同retrofit的create()
     * @param service 请求Api的类
     * @param <T> Api类型
     * @return 请求对象
     */
     public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public <T> T smartRoomCreate(Class<T> service) {
        return mSmartRoomLoginRetrofit.create(service);
    }

}

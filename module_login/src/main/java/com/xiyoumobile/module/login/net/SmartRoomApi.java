package com.xiyoumobile.module.login.net;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SmartRoomApi {

    @GET(Urls.SMART_ROOM_CODE)
    @Headers({"Host: jwkq.xupt.edu.cn:8080", "Referer: http://jwkq.xupt.edu.cn:8080/Account/Login"})
    Observable<ResponseBody> getCode(@Query("time") long time);

    @POST(Urls.SMART_ROOM_LOGIN)
    @Headers({"Host: jwkq.xupt.edu.cn:8080", "Referer: http://jwkq.xupt.edu.cn:8080/Account/Login"})
    Observable<ResponseBody> login(@Body RequestBody s);
}

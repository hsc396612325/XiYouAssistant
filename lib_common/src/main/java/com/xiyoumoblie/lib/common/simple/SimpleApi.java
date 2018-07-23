package com.xiyoumoblie.lib.common.simple;

import com.xiyoumoblie.lib.common.net.NetConstant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SimpleApi {
    @POST(NetConstant.SIMPLE)
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Observable<ResponseBody> getData(@Field("phone") String phone);
}

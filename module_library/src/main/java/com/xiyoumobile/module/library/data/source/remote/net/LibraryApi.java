package com.xiyoumobile.module.library.data.source.remote.net;

import com.xiyoumobile.module.library.data.CommonBean;
import com.xiyoumobile.module.library.data.MainInfo;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LibraryApi {

    @POST(LibraryUrls.LOGIN)
    @FormUrlEncoded
    Observable<CommonBean<Object>> login(@Field("barcode") String id, @Field("password") String pwd);

    @POST(LibraryUrls.GET_MY_INFO)
    @FormUrlEncoded
    Observable<CommonBean<MainInfo>> getMainInfo(@Field("barcode") String id);

    @POST(LibraryUrls.GET_BOOK_LIST)
    @FormUrlEncoded
    Observable<ResponseBody> getBookList(@Field("suchenType") String type, @Field("suchenWord") String word, @Field("curPage") String curPage);

    @POST(LibraryUrls.GET_BOOK_DETAIL)
    @FormUrlEncoded
    Observable<ResponseBody> getBookDetail(@Field("url") String bookUrl);

    @POST(LibraryUrls.GET_MY_BORROWED)
    @FormUrlEncoded
    Observable<ResponseBody> getBorrowed(@Field("barcode") String id);

    @POST(LibraryUrls.GET_MY_HISTORY)
    @FormUrlEncoded
    Observable<ResponseBody> getHistory(@Field("barcode") String id);

    @POST(LibraryUrls.RENEW)
    @FormUrlEncoded
    Observable<ResponseBody> renewBook(@Field("barcode") String id, @Field("bookcode") String bookCode);

}

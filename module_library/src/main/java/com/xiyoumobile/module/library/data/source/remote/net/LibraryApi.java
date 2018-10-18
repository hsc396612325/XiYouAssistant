package com.xiyoumobile.module.library.data.source.remote.net;

import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.CommonBean;
import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.data.SearchData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
    Observable<CommonBean<SearchData>> getBookList(@Field("suchenType") String type, @Field("suchenWord") String word, @Field("curPage") String curPage);

    @POST(LibraryUrls.GET_BOOK_DETAIL)
    @FormUrlEncoded
    Observable<CommonBean<BookDetail>> getBookDetail(@Field("url") String bookUrl);

    @POST(LibraryUrls.GET_MY_BORROWED)
    @FormUrlEncoded
    Observable<BorrowedData> getBorrowed(@Field("barcode") String id);

    @POST(LibraryUrls.GET_MY_HISTORY)
    @FormUrlEncoded
    Observable<HistoryData> getHistory(@Field("barcode") String id);

    @POST(LibraryUrls.RENEW)
    @FormUrlEncoded
    Observable<CommonBean> renewBook(@Field("barcode") String id, @Field("bookcode") String bookCode);

    // 搜索结果
    @POST(LibraryUrls.GET_SEARCH_LIST)
    @FormUrlEncoded
    Observable<ResponseBody> getSearchList(@Field("suchenType") String suchenType, @Field("suchenWord") String suchenWord, @Field("curPage") int curPage);

    @POST(LibraryUrls.LOGOUT)
    @FormUrlEncoded
    Call<ResponseBody> logout(@Field("barcode") String id);
}

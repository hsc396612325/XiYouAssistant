package com.xiyoumobile.module.library.data.source.remote;

import android.util.Log;

import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.CommonBean;
import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.data.SearchData;
import com.xiyoumobile.module.library.data.source.DataSource;
import com.xiyoumobile.module.library.data.source.remote.net.LibraryApi;
import com.xiyoumobile.module.library.data.source.remote.net.RetrofitFactory;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {

    private static volatile RemoteDataSource INSTANCE;
    private static final String TAG = "RemoteDataSource";

    private RemoteDataSource() {}

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void saveIdAndPwd(String id, String pwd) {

    }

    /**
     * 图书馆登录
     * @param id 学号
     * @param pwd 一卡通密码
     * @return 登录是否成功
     */
    @Override
    public Observable<Boolean> login(String id, String pwd) {
        return RetrofitFactory.INSTANCE.create(LibraryApi.class).login(id, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(commonBean -> {
                    switch (commonBean.status) {
                        case 1:
                            return true;
                        default:
                            return false;
                    }
                });
    }

    @Override
    public Observable<MainInfo> getMainInfo(String id) {
        return RetrofitFactory.INSTANCE.create(LibraryApi.class).getMainInfo(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(commonBean -> {
                    if (commonBean.status == 1) {
                        return commonBean.data;
                    }
                    return null;
                });
    }


    @Override
    public Observable<BorrowedData> getBorrowed(String id) {
        return RetrofitFactory.INSTANCE.create(LibraryApi.class).getBorrowed(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<HistoryData> getHistory(String id) {
        return RetrofitFactory.INSTANCE.create(LibraryApi.class).getHistory(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SearchData> getSearchList(String suchenType, String suchenWord, int curPage) {
        return RetrofitFactory.INSTANCE.create(LibraryApi.class).getBookList(suchenType, suchenWord, curPage + "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(searchDataCommonBean -> {
                    if (searchDataCommonBean.status == 1) {
                        return searchDataCommonBean.data;
                    } else {
                        SearchData s = new SearchData();
                        s.totalPage = 0;
                        return s;
                    }
                });
    }

    public void logout(String id) {
        RetrofitFactory.INSTANCE.create(LibraryApi.class).logout(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
//        return RetrofitFactory.INSTANCE.create(LibraryApi.class).logout(id)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BookDetail> getBookDetail(String url) {
        return RetrofitFactory.INSTANCE.create(LibraryApi.class).getBookDetail(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<CommonBean<BookDetail>, BookDetail>() {
                    @Override
                    public BookDetail apply(CommonBean<BookDetail> bookDetailCommonBean) throws Exception {
                        return bookDetailCommonBean.data;
                    }
                });
    }

    public Observable<CommonBean> renew(String id, String bookCode) {
        return RetrofitFactory.INSTANCE.create(LibraryApi.class).renewBook(id, bookCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}

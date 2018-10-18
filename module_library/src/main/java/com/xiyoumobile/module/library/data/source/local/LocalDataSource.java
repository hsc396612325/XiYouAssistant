package com.xiyoumobile.module.library.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.CommonBean;
import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.data.SearchData;
import com.xiyoumobile.module.library.data.source.DataSource;

import io.reactivex.Observable;

public class LocalDataSource implements DataSource {

    private static volatile LocalDataSource INSTANCE;
    private SharedPreferences mPreferences;

    private LocalDataSource(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                     SharedPreferences preferences = context
                             .getSharedPreferences("library_data", Context.MODE_PRIVATE);
                     INSTANCE = new LocalDataSource(preferences);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public String getId() {
        return mPreferences.getString("id", null);
    }

    @Override
    public String getPassword() {
        return mPreferences.getString("pwd", null);
    }

    @Override
    public void saveIdAndPwd(String id, String pwd) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("id", id);
        editor.putString("pwd", pwd);
        editor.apply();
    }

    @Override
    public Observable<Boolean> login(String id, String pwd) {

        return null;
    }

    @Override
    public Observable<MainInfo> getMainInfo(String id) {

        return null;
    }

    @Override
    public Observable<BorrowedData> getBorrowed(String id) {
        return null;
    }

    @Override
    public Observable<HistoryData> getHistory(String id) {
        return null;
    }

    @Override
    public Observable<SearchData> getSearchList(String suchenType, String suchenWord, int curPage) {
        return null;
    }

    @Override
    public void logout(String id) {
        mPreferences.edit().clear().apply();
//        return null;
    }

    @Override
    public Observable<BookDetail> getBookDetail(String url) {

        return null;
    }

    @Override
    public Observable<CommonBean> renew(String id, String bookCode) {
        return null;
    }


}

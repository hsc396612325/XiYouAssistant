package com.xiyoumobile.module.library.data.source;


import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.CommonBean;
import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.data.SearchData;

import io.reactivex.Observable;
public class Repository implements DataSource {

    private static Repository INSTANCE = null;

    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;

    private Repository(DataSource remoteDataSource, DataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }


    public static Repository getInstance(DataSource remoteDataSource, DataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public String getId() {
        return mLocalDataSource.getId();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }

    @Override
    public void saveIdAndPwd(String id, String pwd) {
        mLocalDataSource.saveIdAndPwd(id, pwd);
    }

    @Override
    public Observable<Boolean> login(String id, String pwd) {
        return mRemoteDataSource.login(id, pwd);
    }

    @Override
    public Observable<MainInfo> getMainInfo(String id) {
        mLocalDataSource.getMainInfo(id);
        return mRemoteDataSource.getMainInfo(id);
    }

    @Override
    public Observable<BorrowedData> getBorrowed(String id) {
        return mRemoteDataSource.getBorrowed(id);
    }

    @Override
    public Observable<HistoryData> getHistory(String id) {
        return mRemoteDataSource.getHistory(id);
    }

    @Override
    public Observable<SearchData> getSearchList(String suchenType, String suchenWord, int curPage) {
        return mRemoteDataSource.getSearchList(suchenType, suchenWord, curPage);
    }

    @Override
    public void logout(String id) {
        mLocalDataSource.logout(id);
        /*return*/ mRemoteDataSource.logout(id);
    }

    @Override
    public Observable<BookDetail> getBookDetail(String url) {

        return mRemoteDataSource.getBookDetail(url);
    }

    @Override
    public Observable<CommonBean> renew(String id, String bookCode) {
        return mRemoteDataSource.renew(id, bookCode);
    }

}

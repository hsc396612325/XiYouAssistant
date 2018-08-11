package com.xiyoumobile.module.library.data.source;


import com.xiyoumobile.module.library.data.MainInfo;

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
    public void getBorrowed(String id) {

    }

    @Override
    public void getHistory(String id) {

    }
}

package com.xiyoumobile.module.library.data.source.remote;

import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.data.source.DataSource;
import com.xiyoumobile.module.library.data.source.remote.net.LibraryApi;
import com.xiyoumobile.module.library.data.source.remote.net.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    public void getBorrowed(String id) {

    }

    @Override
    public void getHistory(String id) {

    }
}

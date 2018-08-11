package com.xiyoumobile.module.library.data.source;

import com.xiyoumobile.module.library.data.MainInfo;

import io.reactivex.Observable;

public interface DataSource {

    String getId();

    String getPassword();

    void saveIdAndPwd(String id, String pwd);

    Observable<Boolean> login(String id, String pwd);

    Observable<MainInfo> getMainInfo(String id);

    void getBorrowed(String id);

    void getHistory(String id);

}

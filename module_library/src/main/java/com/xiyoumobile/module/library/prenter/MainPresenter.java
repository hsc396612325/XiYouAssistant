package com.xiyoumobile.module.library.prenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.prenter.contract.MainContract;

@SuppressLint("CheckResult")
public class MainPresenter implements MainContract.Presenter {

    private final Repository mRepository;
    private final MainContract.View mView;

    public MainPresenter(Repository repository, MainContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMainInfo(int tryTime) {
        String id = getId();
        if (id == null) {
            mView.showLoginPage();
            return;
        }
        mView.setLoadingIndicator(true);
        mRepository.getMainInfo(id).subscribe(bookMainInfo -> {
            if (bookMainInfo != null){
                mView.setLoadingIndicator(true);
                mView.refreshMainInfo(bookMainInfo.mainInfos);
            } else if (tryTime == 0) {
                getMainInfoAfterLogin(id, getPwd());
            } else {
                mView.getMainInfoFail();
            }
        });

    }

    private void getMainInfoAfterLogin(String id, String pwd) {
        mView.setLoadingIndicator(true);
        mRepository.login(id, pwd).subscribe(aBoolean -> {
            mView.setLoadingIndicator(false);
            if (aBoolean) {
                getMainInfo(1);
            } else {
                mView.showLoginPage();
            }
        });
    }

    private String getId() {
        return mRepository.getId();
    }

    private String getPwd() {
        return mRepository.getPassword();
    }

}

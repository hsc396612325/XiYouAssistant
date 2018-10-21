package com.xiyoumobile.module.library.l_common.presenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.l_common.presenter.contract.MainContract;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("CheckResult")
public class MainPresenter implements MainContract.Presenter {

    private final Repository mRepository;
    private final MainContract.View mView;

    public MainPresenter(Repository repository, MainContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 悄悄地登录，学号密码从本地获取，如果没有就跳转到登录页
     */
    @Override
    public void privateLogin() {
        String id = getId();
        String pwd = getPwd();
        if (id == null || pwd == null) {
            mView.showLoginPage();
            return;
        }
        mView.setLoadingIndicator(true);
        mRepository.login(id, pwd).subscribe(aBoolean -> {
            if (aBoolean) {
                mRepository.saveIdAndPwd(id, pwd);
                mView.setLoginBtnText(false);
                getMainInfo();
            } else {
                mView.showLoginPage();
            }
        });
        mView.setLoadingIndicator(true);

    }

    /**
     * 登出
     */
    @Override
    public void logout() {
        mRepository.logout(mRepository.getId());
//        mRepository.logout(mRepository.getId()).subscribe(logoutData -> {
            mView.showLoginPage();
//        });
    }

    /**
     * 获取主页需要展示的最近须还图书
     */
    @Override
    public void getMainInfo() {
        String id = getId();
        if (id == null) {
            mView.showLoginPage();
            return;
        }
        mView.setLoadingIndicator(true);
        mRepository.getMainInfo(id).subscribe(bookMainInfo -> {
            if (bookMainInfo != null){
                if (bookMainInfo.count > 0) {
                    mView.refreshMainInfo(bookMainInfo.mainInfos);
                } else {
                    List<MainInfo.MainInfoItem> list = new ArrayList<>();
                    mView.refreshMainInfo(list);
                }
            } else {
                mView.getMainInfoFail();
            }
        });
        mView.setLoadingIndicator(false);
    }

    private String getId() {
        return mRepository.getId();
    }

    private String getPwd() {
        return mRepository.getPassword();
    }

}

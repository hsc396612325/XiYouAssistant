package com.xiyoumobile.module.library.l_common.presenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.l_common.presenter.contract.LoginContract;

@SuppressLint("CheckResult")
public class LoginPresenter implements LoginContract.Presenter {

    private final Repository mRepository;
    private final LoginContract.View mView;

    public LoginPresenter(Repository repository, LoginContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public String getId() {
        String id = mRepository.getId();
        if (id == null) {
            id = "";
        }
        return id;
    }

    @Override
    public String getPwd() {
        String pwd =  mRepository.getPassword();
        if (pwd == null) {
            pwd = "";
        }
        return pwd;
    }

    @Override
    public void saveIdAndPwd() {

    }

    @Override
    public void login(String id, String pwd) {
        mView.setLoadingIndicator(true);
        mRepository.login(id, pwd).subscribe(aBoolean -> {
            mView.setLoadingIndicator(false);
            if (aBoolean) {
                mRepository.saveIdAndPwd(id, pwd);
                mView.onLoginSucc();
            } else {
                mView.onLoginFail();
            }
        });

    }
}

package com.xiyoumobile.module.library.l_common.presenter.contract;

import com.xiyoumobile.module.library.util.BasePresenter;
import com.xiyoumobile.module.library.util.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isShow);

        void onLoginFail();
        void onLoginSucc();

    }

    interface Presenter extends BasePresenter {

        String getId();
        String getPwd();

        void saveIdAndPwd();

        void login(String id, String pwd);

    }
}

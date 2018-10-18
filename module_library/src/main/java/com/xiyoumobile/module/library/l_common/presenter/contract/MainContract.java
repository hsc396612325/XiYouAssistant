package com.xiyoumobile.module.library.l_common.presenter.contract;

import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.util.BasePresenter;
import com.xiyoumobile.module.library.util.BaseView;

import java.util.List;


public interface MainContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isShow);

        void showLoginPage();

        void refreshMainInfo(List<MainInfo.MainInfoItem> mainInfo);

        void getMainInfoFail();

        void setLoginBtnText(boolean b);
    }

    interface Presenter extends BasePresenter {

        void getMainInfo();
        void privateLogin();

        void logout();
    }
}

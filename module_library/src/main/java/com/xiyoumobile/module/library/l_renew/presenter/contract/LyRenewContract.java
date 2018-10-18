package com.xiyoumobile.module.library.l_renew.presenter.contract;

import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.util.BasePresenter;
import com.xiyoumobile.module.library.util.BaseView;

import java.util.List;

public interface LyRenewContract {
    interface View extends BaseView<LyRenewContract.Presenter> {

        void setLoadingIndicator(boolean isShow);

        void getListDataFail();

        void refreshListData(List<BorrowedData.Data> data);

        void gotoLogin();


        void renewSucc();

        void renewFail();
    }

    interface Presenter extends BasePresenter {

        void getRenewList();

        void renew(String bookCode);

    }
}

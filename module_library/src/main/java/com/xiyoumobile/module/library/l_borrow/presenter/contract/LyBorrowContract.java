package com.xiyoumobile.module.library.l_borrow.presenter.contract;

import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.util.BasePresenter;
import com.xiyoumobile.module.library.util.BaseView;

import java.util.List;

public interface LyBorrowContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isShow);

        void getListDataFail();

        void refreshListData(List<BorrowedData.Data> data);

        void gotoLogin();

    }

    interface Presenter extends BasePresenter {

        void getBorrowList();
    }
}

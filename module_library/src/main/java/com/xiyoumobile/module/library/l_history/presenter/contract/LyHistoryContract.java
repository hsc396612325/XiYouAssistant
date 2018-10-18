package com.xiyoumobile.module.library.l_history.presenter.contract;

import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.util.BasePresenter;
import com.xiyoumobile.module.library.util.BaseView;

import java.util.List;

public interface LyHistoryContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isShow);

        void gotoLogin();

        void getListDataFail();

        void refreshListData(List<HistoryData.Data> data);

    }

    interface Presenter extends BasePresenter {

        void getHistory();
    }
}

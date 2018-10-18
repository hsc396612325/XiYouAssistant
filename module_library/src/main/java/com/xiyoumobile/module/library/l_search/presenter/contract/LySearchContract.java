package com.xiyoumobile.module.library.l_search.presenter.contract;

import com.xiyoumobile.module.library.data.SearchData;
import com.xiyoumobile.module.library.util.BasePresenter;
import com.xiyoumobile.module.library.util.BaseView;

import java.util.List;

public interface LySearchContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isShow);

        void getListDataFail();

        void refreshListData(List<SearchData.Data> data);

        void gotoLogin();

    }

    interface Presenter extends BasePresenter {

        void getSearchList(String suchenType, String suchenWord, int curPage);
    }
}

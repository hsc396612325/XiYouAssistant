package com.xiyoumobile.module.library.l_common.presenter.contract;

import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.util.BasePresenter;
import com.xiyoumobile.module.library.util.BaseView;

public interface DetailContract {
    interface View extends BaseView<DetailContract.Presenter> {

        void setLoadingIndicator(boolean isShow);
        void refreshView(BookDetail data);

    }

    interface Presenter extends BasePresenter {
        void getBookDetail(String url);
    }
}

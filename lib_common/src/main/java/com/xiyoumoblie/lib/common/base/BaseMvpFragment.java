package com.xiyoumoblie.lib.common.base;

import com.xiyoumoblie.lib.common.presenter.BasePresenter;
import com.xiyoumoblie.lib.common.presenter.view.BaseView;

public class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    public T mPresenter;

    @Override
    public void start() {

    }
}

package com.xiyoumoblie.lib.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xiyoumoblie.lib.common.presenter.BasePresenter;
import com.xiyoumoblie.lib.common.presenter.view.BaseView;

public class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void start() {

    }
}

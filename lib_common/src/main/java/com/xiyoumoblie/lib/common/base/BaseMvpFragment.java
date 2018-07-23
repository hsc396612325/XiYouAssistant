package com.xiyoumoblie.lib.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xiyoumoblie.lib.common.presenter.BasePresenter;
import com.xiyoumoblie.lib.common.presenter.view.BaseView;

/**
 * 与BaseMvpActivity类似
 * @param <T> 对应Presenter的类型
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    public T mPresenter;

    protected abstract void initMvp();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMvp();
    }

    @Override
    public void start() {

    }
}

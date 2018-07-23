package com.xiyoumoblie.lib.common.presenter;

import com.xiyoumoblie.lib.common.presenter.view.BaseView;

/**
 * Presenter基类
 * @param <T>
 */
public class BasePresenter<T extends BaseView> {
    public T mView;//回调接口
}

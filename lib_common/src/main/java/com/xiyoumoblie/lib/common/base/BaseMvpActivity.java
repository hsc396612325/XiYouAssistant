package com.xiyoumoblie.lib.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xiyoumoblie.lib.common.presenter.BasePresenter;
import com.xiyoumoblie.lib.common.presenter.view.BaseView;

/**
 * 需要MVP的Activity的基类
 * 泛型引入对应Presenter的类型，继承BaseActivity，实现Presenter对应回调View中的抽象方法。
 * @param <T> 对应Presenter的类型
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    //持有的Presenter对象
    protected T mPresenter;

    /**
     * 使用presenter前需要实现该方法初始化mvp
     */
    protected abstract void initMvp();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMvp();
    }

    //BaseView中回调方法的实现
    @Override
    public void start() {
    }

}

package com.xiyoumoblie.lib.common.simple;

import com.xiyoumoblie.lib.common.presenter.view.BaseView;

/**
 * 回调接口，放需要的抽象方法
 */
public interface SimpleView extends BaseView {
    void onSuccess(String msg);
    void onFail(int status, String msg);
}

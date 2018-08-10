
package com.xiyoumoblie.module.education.util;

public interface BaseView<T> {

    /**
     * setPresenter的调用时机是presenter实现类的构造函数中，如此View中的事件请求便通过调用presenter来实现。
     * @param presenter
     */
    void setPresenter(T presenter);

}

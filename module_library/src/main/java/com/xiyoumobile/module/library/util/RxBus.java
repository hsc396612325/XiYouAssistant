package com.xiyoumobile.module.library.util;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private final Subject<Object> mBus;

    private RxBus() {
        this.mBus = PublishSubject.create().toSerialized();
    }

    // 静态内部类单例
    private static class RxBusHolder {
        private static RxBus mInstance = new RxBus();
    }

    // 获取单例
    public static RxBus getInstance() {
        return RxBusHolder.mInstance;
    }

    /**
     * 发送事件
     */
    public void post(Object obj) {
        if (mBus.hasObservers()) {
            mBus.onNext(obj);
        }
    }

    /**
     * 注册观察到事件后的处理
     */
    public <T> Disposable register(Class<T> tClass, Consumer<T> consumer) {
        return mBus.ofType(tClass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * 保存订阅后的disposable
     */
    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    public void addSubscription(Object o, Disposable disposable) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(disposable);
        } else {
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(disposable);
            mSubscriptionMap.put(key, disposables);
        }
    }

    /**
     * 取消订阅
     */
    public void unSubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }

        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)){
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }

        mSubscriptionMap.remove(key);
    }

}

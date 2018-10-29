package com.xiyoumoblie.module.education.net;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;

/**
 * Created by heshu on 2018/10/16.
 */


public class Network {
    public static  Observable io_main( Observable receiver) {
        return receiver.
                subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

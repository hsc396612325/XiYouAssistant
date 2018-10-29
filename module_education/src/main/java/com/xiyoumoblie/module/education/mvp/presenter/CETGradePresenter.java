package com.xiyoumoblie.module.education.mvp.presenter;

import android.util.Log;

import com.xiyoumoblie.module.education.mvp.contract.CETGradeContract;
import com.xiyoumoblie.module.education.mvp.source.CETGradeModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heshu on 2018/8/10.
 */

public class CETGradePresenter implements CETGradeContract.Presenter {

    @NonNull
    private final CETGradeContract.View mView;  //V的实例
    @NonNull
    private final CETGradeModel mModel ; //M的实例

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public CETGradePresenter(@Nullable CETGradeModel model, @NonNull CETGradeContract.View view) {
        //p和V绑定
        mView = view;
        //p和M绑定
        mModel  = model;

        //M和V绑定
        mView.setPresenter(this);

        mCompositeDisposable = new CompositeDisposable();
    }

    @Override //订阅
    public void subscribe() {

    }

    @Override //取消订阅
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override //请求成绩
    public void cetSetGrade(String name, String num, String code) {
        mCompositeDisposable.add(mModel
                .cetGrade(name, num, code)
                .subscribeOn(Schedulers.io()) //提供Scheduler用于Rxjava线程调度
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        CETGradeBean -> {
                            if (CETGradeBean == null) {
                                mView.cetFailure("信息有误"); //调用页面出错
                            } else {
                                mView.cetShowGrade(CETGradeBean);
                            }
                        },
                        // onError
                        throwable -> mView.cetFailure("信息有误"))); //调用页面出错
    }

    @Override
    public void cetSetValidateCode(String num) {
        mCompositeDisposable.add(mModel
                .cetValidateCode(num)
                .subscribeOn(Schedulers.io()) //提供Scheduler用于Rxjava线程调度
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                         cetGradeBean -> {
                            if (cetGradeBean == null) {
                                mView.cetFailure("信息有误1"); //调用页面出错
                            } else {
                                mView.cetShowValidateCode(cetGradeBean);
                            }
                        },
                        // onError
                        throwable -> {
                            mView.cetFailure("信息有误2");
                            Log.d("11111", "cetSetValidateCode: "+throwable);
                        })); //调用页面出错
    }
}

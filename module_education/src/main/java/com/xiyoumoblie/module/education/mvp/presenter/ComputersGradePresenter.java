package com.xiyoumoblie.module.education.mvp.presenter;

import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.mvp.contract.ComputersGradeContract;
import com.xiyoumoblie.module.education.mvp.source.ComputersGradeModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heshu on 2018/8/8.
 */

public class ComputersGradePresenter implements ComputersGradeContract.Presenter {

    @NonNull
    private final ComputersGradeContract.View mView;  //V的实例
    @NonNull
    private final ComputersGradeModel mModel; //M的实例

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private static final String TAG = "ComputersGradePresenter";
    public ComputersGradePresenter(@Nullable ComputersGradeModel model,
                                   @NonNull ComputersGradeContract.View view) {

        //p和V绑定
        mView = view;
        //p和V绑定
        mModel = model;
        //M和V绑定
        mView.setPresenter(this);

        mCompositeDisposable = new CompositeDisposable();
    }


    //订阅
    @Override
    public void subscribe() {
        cgSetTimes();
        cgSetValidateCode();
    }

    //取消订阅
    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }


    public void cgSetTimes() {
        mCompositeDisposable.add(mModel
                .cgTimes()
                .subscribeOn(Schedulers.io()) //提供Scheduler用于Rxjava线程调度
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        cgTimes-> {
                            if (cgTimes == null) {
                                mView.cgFailure("网络请求失败"); //调用页面出错
                            } else {
                                mView.cgGetTimes(cgTimes);
                            }
                        },
                        // onError
                        throwable -> mView.cgFailure("网络请求失败")));//调用页面出错
    }

    @Override
    public void cgSetValidateCode() {

        mCompositeDisposable.add(mModel
                .cgValidateCode()
                .subscribeOn(Schedulers.io()) //提供Scheduler用于Rxjava线程调度
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        bitmap-> {
                            if (bitmap == null) {
                                mView.cgFailure("网络请求失败1"); //调用页面出错
                            } else {
                                mView.cgShowValidateCode(bitmap);
                            }
                        },
                        // onError
                        throwable -> mView.cgFailure("网络请求失败2"))); //调用页面出错
    }

    @Override
    public void cgSetGrade(CgDemandDate cgDemandDate) {

        mCompositeDisposable.add(mModel
                .cgGrade(cgDemandDate)
                .subscribeOn(Schedulers.io()) //提供Scheduler用于Rxjava线程调度
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        cgQuery-> {
                            if (cgQuery == null) {
                                mView.cgFailure("信息有误"); //调用页面出错
                            } else {
                                mView.cgShowGrade(cgQuery);
                            }
                        },
                        // onError
                        throwable -> mView.cgFailure("信息有误"))); //调用页面出错
    }
}

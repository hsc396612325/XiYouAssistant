package com.xiyoumoblie.module.education.presenter;

import android.util.Log;

import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.contract.CETGradeContract;
import com.xiyoumoblie.module.education.source.EducationRepository;

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
    private final EducationRepository mEducationRepository; //M的实例

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public CETGradePresenter(@Nullable EducationRepository educationRepository, @NonNull CETGradeContract.View view) {
        //p和V绑定
        mView = view;
        //p和M绑定
        mEducationRepository = educationRepository;

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
        mCompositeDisposable.add(mEducationRepository
                .CETGrade(name, num, code)
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
        mCompositeDisposable.add(mEducationRepository
                .CETValidateCode(num)
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

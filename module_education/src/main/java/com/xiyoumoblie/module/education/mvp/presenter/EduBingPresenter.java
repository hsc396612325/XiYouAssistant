package com.xiyoumoblie.module.education.mvp.presenter;

import android.util.Log;

import com.xiyoumoblie.module.education.bean.EduBind.EdUserBean;
import com.xiyoumoblie.module.education.mvp.contract.EduBingContract;
import com.xiyoumoblie.module.education.mvp.source.EduBingModel;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by heshu on 2018/8/11.
 */

public class EduBingPresenter implements EduBingContract.Presenter {


    @NonNull
    private final EduBingContract.View mView;  //V的实例
    @NonNull
    private final EduBingModel mModel; //M的实例

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public EduBingPresenter(@Nullable EduBingModel model,
                                   @NonNull EduBingContract.View view) {

        //p和V绑定
        mView = view;
        //p和V绑定
        mModel = model;
        //M和V绑定
        mView.setPresenter(this);

        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void eduSetPublicKey(long time) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url("http://www.zfjw.xupt.edu.cn/jwglxt/xtgl/login_getPublicKey.html?time=1539509678717&_=1539509678488").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String url = response.body().string();
                    System.out.println(url);
                    String publicKey = url.substring(30, url.length() - 2);
                    String data = "waxi1998JTT.";
                    System.out.println(publicKey);
                    Log.d("aaa", "onResponse: "+publicKey);
//                    System.out.println(getPubKey(publicKey));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        Log.d("公钥", "Time: "+time);
        mCompositeDisposable.add(mModel
                .eduPublicKey(time)
                .subscribeOn(Schedulers.io()) //提供Scheduler用于Rxjava线程调度
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        key-> {
                            if (key == null) {
                                mView.eduFailure("网络请求失败"); //调用页面出错
                            } else {
                                Log.d("公钥", "eduSetPublicKey: "+key);
                            }

                            Log.d("公钥", "eduSetPublicKey: "+key);
                        },
                        // onError
                        throwable -> mView.eduFailure("网络请求失败")));//调用页面出错
    }

    @Override
    public void eduLogin(EdUserBean edUserBean) {
//        mCompositeDisposable.add(mModel
//                .eduLogin(edUserBean)
//                .subscribeOn(Schedulers.io()) //提供Scheduler用于Rxjava线程调度
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        // onNext
//                        loginBean -> {
//                            if (loginBean == null) {
//                                mView.eduFailure("网络请求失败"); //调用页面出错
//                            } else {
//                                mView.eduLogin(loginBean);
//                            }
//                        },
//                        // onError
//                        throwable -> mView.eduFailure("网络请求失败")));//调用页面出错
    }
}

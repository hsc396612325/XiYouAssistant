package com.xiyoumoblie.lib.common.simple;

import com.xiyoumoblie.lib.common.net.RetrofitFactory;
import com.xiyoumoblie.lib.common.presenter.BasePresenter;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * SimpleActivity的Presenter
 */
public class SimplePresenter extends BasePresenter<SimpleView> {

    public Disposable getNetworkData(String phone) {
        //调用封装好的retrofit对象，调用传入Api的请求方法
        return RetrofitFactory.INSTANCE.create(SimpleApi.class).getData(phone)
                //rx处理解析response
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(responseBody -> {
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    int status = jsonObject.getInt("status");
                    String msg = jsonObject.getString("msg");
                    //根据结果调用不同的回调方法
                    if (status == 0) {
                        mView.onSuccess(msg);
                    } else {
                        mView.onFail(status, msg);
                    }
                });


    }

}

package com.xiyoumoblie.lib.common.simple;

import android.os.Bundle;
import android.widget.Toast;

import com.xiyoumoblie.lib.common.R;
import com.xiyoumoblie.lib.common.base.BaseMvpActivity;
import com.xiyoumoblie.lib.common.utils.Utils;

import io.reactivex.disposables.Disposable;

public class SimpleActivity extends BaseMvpActivity<SimplePresenter> implements SimpleView {

    Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        //此处调用presenter的方法，开始网络请求
        mDisposable = mPresenter.getNetworkData("110");

    }

    //此方法用于绑定presenter和view，必须实现
    @Override
    protected void initMvp() {
        mPresenter = new SimplePresenter();
        mPresenter.mView = this;
    }

    //SimpleView的回调实现
    @Override
    public void onSuccess(String msg) {
        Toast.makeText(Utils.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //SimpleView的回调实现
    @Override
    public void onFail(int status, String msg) {
        Toast.makeText(Utils.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //rx回收
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}

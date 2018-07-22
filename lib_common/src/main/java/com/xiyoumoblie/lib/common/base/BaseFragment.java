package com.xiyoumoblie.lib.common.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Fragment的基类
 */
public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取Fragment所属的Activity
        this.mActivity = (BaseActivity) context;
    }

    /**
     * 获取宿主Activity
     *
     * @return BaseActivity
     */
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    //调用BaseActivity封装的操作
    protected void add(@NonNull BaseFragment fragment, @IdRes int frameId) {
        getHoldingActivity().addFragment(fragment, frameId);
    }

    protected void replace(@NonNull BaseFragment fragment, @IdRes int frameId) {
        getHoldingActivity().replaceFragment(fragment, frameId);
    }

    protected void hide(@NonNull BaseFragment fragment) {
        getHoldingActivity().hideFragment(fragment);
    }

    protected void show(@NonNull BaseFragment fragment) {
        getHoldingActivity().showFragment(fragment);
    }

    protected void remove(@NonNull BaseFragment fragment) {
        getHoldingActivity().removeFragment(fragment);
    }


}

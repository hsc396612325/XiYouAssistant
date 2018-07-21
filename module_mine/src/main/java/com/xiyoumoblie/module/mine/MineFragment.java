package com.xiyoumoblie.module.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseFragment;

@Route(path = "/mine/list")
public class MineFragment extends BaseFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        return view;
    }
}

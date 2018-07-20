package com.xiyoumobile.xiyouassistant.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiyoumobile.xiyouassistant.R;
import com.xiyoumobile.xiyouassistant.base.ui.BaseFragment;

public class MineFragment extends BaseFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        return view;
    }
}

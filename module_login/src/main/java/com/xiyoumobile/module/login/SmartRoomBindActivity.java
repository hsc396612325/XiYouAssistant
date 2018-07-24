package com.xiyoumobile.module.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xiyoumoblie.lib.common.base.BaseActivity;

public class SmartRoomBindActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private TextView mTvFrameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("绑定智慧教室");

        mTvFrameTitle = findViewById(R.id.frame_title);
        mTvFrameTitle.setText("智慧教室登录");

    }
}

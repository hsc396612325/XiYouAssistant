package com.xiyoumobile.module.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiyoumoblie.lib.common.base.BaseActivity;

public class EduBindActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

    }

    public void onClickChangeCode(View view) {

    }


    public void onClickLogin(View view) {

    }
}

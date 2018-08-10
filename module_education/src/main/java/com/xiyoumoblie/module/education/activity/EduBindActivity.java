package com.xiyoumoblie.module.education.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.module.education.R;

@Route(path = "/education/EduBind")
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

package com.xiyoumobile.module.library.l_schedule.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseActivity;

public class LyOpenTimeActivity extends BaseActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_open_time);

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

    }
}

package com.xiyoumobile.module.library.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseActivity;

public class LyDistributionActivity extends BaseActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_distribution);

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);
    }
}

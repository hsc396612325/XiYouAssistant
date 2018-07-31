package com.xiyoumoblie.module.education.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.module.education.R;

public class TimetableAddActivity extends BaseActivity{
    private Toolbar mToolbar;
    private TextView mTvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_add);

        mToolbar = findViewById(R.id.timetable_add_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("添加课程");
        setupToolBar(mToolbar, true);
    }
}

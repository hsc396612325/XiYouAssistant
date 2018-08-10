package com.xiyoumoblie.module.education.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.module.education.R;

/**
 * 课表详情界面
 */
public class TimetableCourseActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_course);

        mToolbar = findViewById(R.id.timetable_course_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("课程详情");
        setupToolBar(mToolbar, true);

    }
}

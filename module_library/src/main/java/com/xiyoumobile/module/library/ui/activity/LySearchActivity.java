package com.xiyoumobile.module.library.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseActivity;

public class LySearchActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "LySearchActivity";
    private Toolbar mToolbar;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonDefault;
    private Button mButton;
    private int mSearchType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_search);

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

        mRadioGroup = findViewById(R.id.lb_rg);
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioButtonDefault = findViewById(R.id.lb_rb_name);
        mRadioButtonDefault.setChecked(true);

        mButton = findViewById(R.id.bt_search);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LySearchActivity.this, LyListActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.lb_rb_name) {
            mSearchType = 0;

        } else if (checkedId == R.id.lb_rb_search_num) {
            mSearchType = 1;

        } else if (checkedId == R.id.lb_rb_standard_num) {
            mSearchType = 2;

        } else if (checkedId == R.id.lb_rb_author) {
            mSearchType = 3;

        } else if (checkedId == R.id.lb_rb_publish) {
            mSearchType = 4;

        }
    }

}

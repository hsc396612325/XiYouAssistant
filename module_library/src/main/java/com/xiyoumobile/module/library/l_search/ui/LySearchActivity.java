package com.xiyoumobile.module.library.l_search.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseActivity;

public class LySearchActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "LySearchActivity";
    private Toolbar mToolbar;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonDefault;
    private Button mButton;
    private String mSearchType = "0";
    private EditText mEditText;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_search);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

        mContext = this;

        mRadioGroup = findViewById(R.id.lb_rg);
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioButtonDefault = findViewById(R.id.lb_rb_name);
        mRadioButtonDefault.setChecked(true);
        mEditText = findViewById(R.id.et);

        mButton = findViewById(R.id.bt_search);
        mButton.setOnClickListener(v -> {
            String words = mEditText.getText().toString();
            if (TextUtils.isEmpty(words)) {
                Toast.makeText(mContext, "请输入关键字！", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(LySearchActivity.this, LySearchListActivity.class);
            intent.putExtra("title", "检索结果");
            intent.putExtra("kind", mSearchType);
            intent.putExtra("words", words);
            startActivity(intent);
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.lb_rb_name) {
            mSearchType = "1";

        } else if (checkedId == R.id.lb_rb_search_num) {
            mSearchType = "2";

        } else if (checkedId == R.id.lb_rb_standard_num) {
            mSearchType = "3";

        } else if (checkedId == R.id.lb_rb_author) {
            mSearchType = "4";

        } else if (checkedId == R.id.lb_rb_publish) {
            mSearchType = "5";

        }
    }

}

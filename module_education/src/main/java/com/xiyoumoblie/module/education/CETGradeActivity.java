package com.xiyoumoblie.module.education;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;

@Route(path = "/education/CETGrade")
public class CETGradeActivity extends BaseActivity{
    private Toolbar mToolbar;
    private TextView mTvTitle;

    private FrameLayout mFrameLayout;
    private Button mBtConfirm;

    private EditText mEtName;
    private EditText mEtNum;

    private MyTextView mTvAudition;
    private MyTextView mTvRead;
    private MyTextView mTvComposition;
    private MyTextView mTvTotal;
    private MyTextView mTvTongue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cet_grade);

        initView();
        initData();

    }

    private void initView(){
        mToolbar = findViewById(R.id.cet_grade_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("四六级成绩查询");
        setupToolBar(mToolbar, true);

        mFrameLayout = (FrameLayout)findViewById(R.id.fl);
        float curTranslationX = mFrameLayout.getTranslationX();
        float curTranslationY = mFrameLayout.getTranslationY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFrameLayout, "translationX", curTranslationX, -1200);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mFrameLayout, "translationY", curTranslationY, 1000);
        animator.setDuration(1500);
        animator.setInterpolator( new AccelerateInterpolator());
        animator2.setDuration(1500);
        animator2.setInterpolator( new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator,animator2);

        mBtConfirm = (Button)findViewById(R.id.bt_confirm);
        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatorSet.start();
            }
        });

        mEtName = (EditText)findViewById(R.id.et_name);
        mEtNum = (EditText)findViewById(R.id.et_num);

        mTvAudition = (MyTextView)findViewById(R.id.tv_audition);
        mTvRead = (MyTextView)findViewById(R.id.tv_read);
        mTvComposition = (MyTextView)findViewById(R.id.tv_composition);
        mTvTotal = (MyTextView)findViewById(R.id.tv_total);
        mTvTongue = (MyTextView)findViewById(R.id.tv_tongue);
    }

    private void initData(){
        mTvAudition.setText("200");
        mTvRead.setText("200");
        mTvComposition.setText("190");
        mTvTotal.setText("590");
        mTvTongue.setText("A");
    }
}

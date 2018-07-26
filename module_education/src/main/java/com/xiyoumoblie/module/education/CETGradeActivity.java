package com.xiyoumoblie.module.education;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;

@Route(path = "/education/CETGrade")
public class CETGradeActivity extends BaseActivity{
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private FrameLayout mFrameLayout;

    private Button mBtConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cet_grade);


        mToolbar = findViewById(R.id.appeal_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("考勤申诉");
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
        animatorSet.playSequentially(animator,animator2);

        mBtConfirm = (Button)findViewById(R.id.bt_confirm);
        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animatorSet.start();
            }
        });

    }

}

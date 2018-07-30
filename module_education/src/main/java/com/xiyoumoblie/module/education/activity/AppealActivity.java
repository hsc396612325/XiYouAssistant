package com.xiyoumoblie.module.education.activity;

import android.os.Bundle;

import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.bean.LessonInfoBean;
import com.xiyoumoblie.module.education.R;

import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class AppealActivity extends BaseActivity {

    private Toolbar mToolbar;
    private MyTextView mTvTitle;

    private  MyTextView mTvCourse;
    private  MyTextView mTVDate;
    private EditText mEtAppeal;
    private CardView mCardViewButton;

    private static final String TAG = "AppealActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appeal);

        LessonInfoBean lessonInfoBean = (LessonInfoBean) getIntent().getSerializableExtra("lessonInfo");
        initView();
        if (lessonInfoBean != null) {
            initData(lessonInfoBean);
        }
        initView();


    }

    private void initView() {
        mToolbar = findViewById(R.id.appeal_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("考勤申诉");
        setupToolBar(mToolbar, true);

        mTvCourse = ( MyTextView) findViewById(R.id.tv_course);
        mTVDate = ( MyTextView) findViewById(R.id.tv_date);
        mEtAppeal = (EditText) findViewById(R.id.et_appeal);
        mCardViewButton = (CardView) findViewById(R.id.cardView2);

        mCardViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mEtAppeal.getText().length()<7){
                    Toast.makeText(AppealActivity.this,"字数不足",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AppealActivity.this,"正在申诉....",Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onClick: ");
            }
        });
    }

    private void initData(LessonInfoBean lessonInfoBean){
        mTvCourse.setText(lessonInfoBean.getCourse());
        String str = lessonInfoBean.getDate() +" " +lessonInfoBean.getLesson();
        mTVDate.setText(str);

    }
}

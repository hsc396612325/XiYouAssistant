package com.xiyoumoblie.module.education.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.R;

import java.util.ArrayList;

@Route(path = "/education/ComputersGrade")
public class ComputersGradeActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private FrameLayout mFrameLayout;

    private Button mBtConfirm;

    private LinearLayout mLlDate;
    private MyTextView mTvDate;
    private LinearLayout mLlType;
    private MyTextView mTvType;
    private EditText mEtName;
    private EditText mEtNum;
    private EditText mEtCode;

    private MyTextView mTvType2;
    private MyTextView mTvGrade;
    private MyTextView mTvCredential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computers_grade);

        initView();
        initData();
    }

    private void initView() {
        mToolbar = findViewById(R.id.computers_grade_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("计算机等级成绩");
        setupToolBar(mToolbar, true);

        mFrameLayout = (FrameLayout) findViewById(R.id.fl);
        float curTranslationX = mFrameLayout.getTranslationX();
        float curTranslationY = mFrameLayout.getTranslationY();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(mFrameLayout, "translationX", curTranslationX, -1200),
                ObjectAnimator.ofFloat(mFrameLayout, "translationY", curTranslationY, 1000),
                ObjectAnimator.ofFloat(mFrameLayout, "alpha", 1f, 0.5f)
        );

        animatorSet.setDuration(2000);
        mBtConfirm = (Button) findViewById(R.id.bt_confirm);
        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animatorSet.start();
            }
        });

        mLlDate = (LinearLayout) findViewById(R.id.ll_data);
        mLlType = (LinearLayout) findViewById(R.id.ll_type);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtNum = (EditText) findViewById(R.id.et_num);
        mEtCode = (EditText) findViewById(R.id.et_code);
        mTvType = (MyTextView) findViewById(R.id.tv_type);
        mTvDate = (MyTextView) findViewById(R.id.tv_date);


        mTvType2 = (MyTextView) findViewById(R.id.tv_type2);
        mTvGrade = (MyTextView) findViewById(R.id.tv_grade);
        mTvCredential = (MyTextView) findViewById(R.id.tv_grade);

        OptionsPickerView lessonDatePickerView = getDateOptionPickerView(mLlType);
        mLlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lessonDatePickerView.show();
            }
        });


        OptionsPickerView lessonTypePickerView = getTypeOptionPickerView(mLlType);  //获取OptionPickerView
        mLlType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lessonTypePickerView.show();
            }
        });
    }

    private void initData() {

    }

    private OptionsPickerView getDateOptionPickerView(final View view) {
        final ArrayList<String> lessons = getDateLessons();
        OptionsPickerView tempPickView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String temp = lessons.get(options1);
                mTvDate.setText(temp);
            }

        }).setTitleText("时间选择").build();
        tempPickView.setPicker(lessons);
        return tempPickView;
    }

    private ArrayList<String> getDateLessons() {
        ArrayList<String> lessons = new ArrayList<>();

        lessons.add("2018年06月");
        lessons.add("2018年03月");
        lessons.add("2018年12月");
        lessons.add("2018年09月");
        return lessons;
    }

    private OptionsPickerView getTypeOptionPickerView(final View view) {
        final ArrayList<String> lessons = getTypeLessons();
        OptionsPickerView tempPickView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String temp = lessons.get(options1);
                mTvType.setText(temp);
                mTvType2.setText(temp);
            }

        })
                .setTitleText("类型选择")
                .build();
        tempPickView.setPicker(lessons);
        return tempPickView;
    }

    private ArrayList<String> getTypeLessons() {
        ArrayList<String> lessons = new ArrayList<>();

        lessons.add("14一级计算机基础及WPS Office应用");
        lessons.add("15一级计算机基础及Ms Office应用");
        lessons.add("16一级计算机基础机PhotoShop应用");
        lessons.add("24二级C语言程序设计");
        lessons.add("26二级VB语言程序设计");
        lessons.add("27二级VFP语言程序设计");
        lessons.add("28二级JAVA语言程序设计");
        lessons.add("29二级Access数据库程序设计");
        lessons.add("61二级C++语言程序设计");
        lessons.add("63二级MySQL数据库程序设计");
        lessons.add("64二级Web程序设计");
        lessons.add("65二级MS Office高级应用");
        lessons.add("66二级Python 语言程序设计");

        lessons.add("35三级网络技术 ");
        lessons.add("36三级数据库技术");
        lessons.add("38三级信息安全技术");
        lessons.add("39三级嵌入式系统开发技术");

        lessons.add("41三级网络工程师");
        lessons.add("42三级数据库工程师 ");
        lessons.add("44三级信息安全工程师");
        lessons.add("45三级嵌入式系统开发工程师 ");

        return lessons;
    }
}


package com.xiyoumoblie.module.education.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.adapter.FinalGradeAdapter;
import com.xiyoumoblie.module.education.bean.FinalGradeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 期末成绩查询
 */
@Route(path = "/education/FinalGrade")
public class FinalGradeActivity extends BaseActivity {
    private TextView mTvTerm;           //2017-2018第一学期
    private TextView mTvAverageGpa;     //平均绩点：5.0
    private TextView mTvsumGpa;         //总绩点
    private TextView mTvBCreditNeed;    //必修所需学分
    private TextView mTvBCreditGain;
    private TextView mTvBCreditNotpass;
    private TextView mTvXCreditNeed;
    private TextView mTvXCreditGain;
    private TextView mTvXCreditNotpass;

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private List<FinalGradeBean> mFinalGradeBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_grade);

        initView();
        initCourse();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_course);
        FinalGradeAdapter adapter = new  FinalGradeAdapter( mFinalGradeBeanList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void initView(){
        mToolbar = findViewById(R.id.final_grade_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("成绩详情");
        setupToolBar(mToolbar, true);


        mTvAverageGpa = (TextView) findViewById(R.id.average_gpa);
        mTvsumGpa = (TextView) findViewById(R.id.sum_gpa);
        mTvBCreditNeed = (TextView) findViewById(R.id.b_credit_need);
        mTvBCreditGain = (TextView) findViewById(R.id.b_credit_gain);
        mTvBCreditNotpass = (TextView) findViewById(R.id.b_credit_notpass);
        mTvXCreditNeed = (TextView) findViewById(R.id.x_credit_need);
        mTvXCreditGain = (TextView) findViewById(R.id.x_credit_gain);
        mTvXCreditNotpass = (TextView) findViewById(R.id.x_credit_notpass);
    }

    public void initCourse() {
        mFinalGradeBeanList = new ArrayList<>();
        for(int i = 0;i < 10;i++) {
            FinalGradeBean finalGradeBean = new FinalGradeBean("C语言程序设计", "必", "4", "30", "70", "100", "5.0");
            mFinalGradeBeanList.add(finalGradeBean);
        }
    }
}

package com.xiyoumoblie.module.education.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.lib.common.utils.Utils;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;
import com.xiyoumoblie.module.education.mvp.contract.ComputersGradeContract;
import com.xiyoumoblie.module.education.mvp.presenter.ComputersGradePresenter;
import com.xiyoumoblie.module.education.mvp.source.ComputersGradeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function5;

@Route(path = "/education/ComputersGrade")
public class ComputersGradeActivity extends BaseActivity implements ComputersGradeContract.View {
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private CardView mCardView;

    private Button mBtConfirm;

    private LinearLayout mLlDate;
    private MyTextView mTvDate;
    private LinearLayout mLlType;
    private MyTextView mTvType;
    private EditText mEtName;
    private EditText mEtNum;
    private EditText mEtCode;
    private ImageView mIvCode;
    private MyTextView mTvType2;
    private MyTextView mTvGrade;
    private MyTextView mTvCredential;

    private AnimatorSet mAnimatorSet;

    private ComputersGradeContract.Presenter mPresenter;

    private TreeMap<String, String> mDateSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computers_grade);

        initView();
        clickJudge();

        //创建一个P实例
        //参数1 M的实例
        //参数2 V的实例
        new ComputersGradePresenter(new ComputersGradeModel(), this);
    }

    private void initView() {
        mToolbar = findViewById(R.id.computers_grade_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("计算机等级成绩");
        setupToolBar(mToolbar, true);

        mCardView = (CardView) findViewById(R.id.cardView2);
        float curTranslationX = mCardView.getTranslationX();
        float curTranslationY = mCardView.getTranslationY();

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(mCardView, "translationX", curTranslationX, -1200),
                ObjectAnimator.ofFloat(mCardView, "translationY", curTranslationY, 1000),
                ObjectAnimator.ofFloat(mCardView, "alpha", 1f, 0.5f)
        );

        mAnimatorSet.setDuration(2000);
        mBtConfirm = (Button) findViewById(R.id.bt_confirm);
        mBtConfirm.setOnClickListener(view -> {

            CgDemandDate cgDemandDate = new CgDemandDate(mDateSet.get(mTvDate.getText().toString()).toString(), mTvType.getText().subSequence(0, 2).toString(),
                    mEtName.getText().toString(), mEtNum.getText().toString(), mEtCode.getText().toString());

            Log.d("V-->", "initView: " + cgDemandDate);
            mPresenter.cgSetGrade(cgDemandDate);
        });

        mLlDate = (LinearLayout) findViewById(R.id.ll_data);
        mLlType = (LinearLayout) findViewById(R.id.ll_type);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtNum = (EditText) findViewById(R.id.et_num);
        mEtCode = (EditText) findViewById(R.id.et_code);
        mIvCode = (ImageView) findViewById(R.id.iv_code);

        mTvType = (MyTextView) findViewById(R.id.tv_type);
        mTvDate = (MyTextView) findViewById(R.id.tv_date);


        mTvType2 = (MyTextView) findViewById(R.id.tv_type2);
        mTvGrade = (MyTextView) findViewById(R.id.tv_grade);
        mTvCredential = (MyTextView) findViewById(R.id.tv_credential);

        OptionsPickerView lessonTypePickerView = getTypeOptionPickerView(mLlType);  //获取OptionPickerView
        mLlType.setOnClickListener(view -> lessonTypePickerView.show());
        mIvCode.setOnClickListener(view -> mPresenter.cgSetValidateCode());

    }

    private void clickJudge() {
        Observable<CharSequence> dateObservable = RxTextView.textChanges(mTvDate).skip(0);
        Observable<CharSequence> typeObservable = RxTextView.textChanges(mTvType).skip(1);
        Observable<CharSequence> nameObservable = RxTextView.textChanges(mEtName).skip(1);
        Observable<CharSequence> numObservable = RxTextView.textChanges(mEtNum).skip(1);
        Observable<CharSequence> codeObservable = RxTextView.textChanges(mEtCode).skip(1);

        Observable.combineLatest(dateObservable, typeObservable,
                nameObservable, numObservable, codeObservable,
                new Function5<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {

                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2,
                                         CharSequence charSequence3, CharSequence charSequence4,
                                         CharSequence charSequence5) throws Exception {


                        boolean isUserDateValid = !TextUtils.isEmpty(mTvDate.getText());
                        boolean isUserTypeValid = !TextUtils.isEmpty(mTvType.getText());
                        boolean isUserNameValid = !TextUtils.isEmpty(mEtName.getText());
                        boolean isUserNumValid = !TextUtils.isEmpty(mEtNum.getText());
                        boolean isUserCodeValid = !TextUtils.isEmpty(mEtCode.getText());
                        return isUserDateValid && isUserTypeValid && isUserNameValid && isUserNumValid && isUserCodeValid;

                    }

                }).subscribe(aBoolean -> mBtConfirm.setEnabled(aBoolean));
    }

    private OptionsPickerView getDateOptionPickerView(CgTimes cgTimes) {

        final List<String> lessons = new ArrayList<>(cgTimes.data.keySet());
        mTvDate.setText(lessons.get(lessons.size() - 1));
        OptionsPickerView tempPickView = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String temp = lessons.get(options1);
            mTvDate.setText(temp);
        }).setTitleText("时间选择").build();

        tempPickView.setPicker(lessons);
        return tempPickView;
    }

    private OptionsPickerView getTypeOptionPickerView(final View view) {
        final ArrayList<String> lessons = getTypeLessons();
        OptionsPickerView tempPickView = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String temp = lessons.get(options1);
            mTvType.setText(temp);
            mTvType2.setText(temp);
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

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }


    @Override
    public void setPresenter(@NonNull ComputersGradeContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override //展示时间
    public void cgGetTimes(CgTimes cgTimes) {
        mDateSet = cgTimes.data;
        OptionsPickerView lessonDatePickerView = getDateOptionPickerView(cgTimes);
        mLlDate.setOnClickListener(view -> lessonDatePickerView.show());
    }


    @Override //展示验证码
    public void cgShowValidateCode(Bitmap bitmap) {
        mIvCode.setImageBitmap(bitmap);
    }


    @Override
    public void cgShowGrade(CgQuery cgQuery) {
        if(cgQuery.status == 500){
            Toast.makeText(Utils.getContext(), "信息有误", Toast.LENGTH_SHORT).show();
        }else if(cgQuery.data == null){
            Toast.makeText(Utils.getContext(), "验证码错误", Toast.LENGTH_SHORT).show();
            mPresenter.cgSetValidateCode();
        }else {
            mAnimatorSet.start();
            mTvGrade.setText(cgQuery.data.status);
            mTvCredential.setText(cgQuery.data.zkzh);
        }
    }

    @Override //请求失败
    public void cgFailure(String str) {
        Toast.makeText(Utils.getContext(), str, Toast.LENGTH_SHORT).show();

    }
}


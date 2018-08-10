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
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.lib.common.utils.Utils;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.contract.CETGradeContract;
import com.xiyoumoblie.module.education.presenter.CETGradePresenter;
import com.xiyoumoblie.module.education.util.Injection;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;

/**
 * 四六级查询界面
 */
@Route(path = "/education/CETGrade")
public class CETGradeActivity extends BaseActivity implements CETGradeContract.View {
    private Toolbar mToolbar;
    private TextView mTvTitle;

    private CardView mCardView;
    private Button mBtConfirm;

    private EditText mEtName;
    private EditText mEtNum;
    private EditText mEtCode;
    private ImageView mIvCode;
    private LinearLayout mLlCode;
    private MyTextView mTvAudition;
    private MyTextView mTvRead;
    private MyTextView mTvComposition;
    private MyTextView mTvTotal;
    private MyTextView mTvTongue;

    private CETGradeContract.Presenter mPresenter;

    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cet_grade);

        initView();
        initData();

        clickJudge();
        //创建一个P实例
        //参数1 M的实例
        //参数2 V的实例
        new CETGradePresenter(Injection.provideTasksRepository(getApplicationContext()), this);

    }

    private void initView() {
        mToolbar = findViewById(R.id.cet_grade_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("四六级成绩查询");
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
        mBtConfirm.setOnClickListener(v -> mPresenter.cetSetGrade(mEtName.getText().toString(), mEtNum.getText().toString(), mEtCode.getText().toString()));

        mEtName = (EditText) findViewById(R.id.et_name);
        mEtNum = (EditText) findViewById(R.id.et_num);
        mEtCode = (EditText) findViewById(R.id.et_code);
        mIvCode = (ImageView) findViewById(R.id.iv_code);
        mLlCode = (LinearLayout) findViewById(R.id.ll_code);

        mIvCode.setOnClickListener(v -> mPresenter.cetSetValidateCode(mEtNum.getText().toString()));

        mTvAudition = (MyTextView) findViewById(R.id.tv_audition);
        mTvRead = (MyTextView) findViewById(R.id.tv_read);
        mTvComposition = (MyTextView) findViewById(R.id.tv_composition);
        mTvTotal = (MyTextView) findViewById(R.id.tv_total);
        mTvTongue = (MyTextView) findViewById(R.id.tv_tongue);
    }

    private void initData() {
        mTvAudition.setText("200");
        mTvRead.setText("200");
        mTvComposition.setText("190");
        mTvTotal.setText("590");
        mTvTongue.setText("A");
    }

    private void clickJudge() {
        Observable<CharSequence> nameObservable = RxTextView.textChanges(mEtName).skip(1);
        Observable<CharSequence> numObservable = RxTextView.textChanges(mEtNum).skip(1);

        Observable.combineLatest(
                nameObservable, numObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {

                    public Boolean apply(CharSequence charSequence1, CharSequence charSequence2) throws Exception {


                        boolean isUserNameValid = !TextUtils.isEmpty(mEtName.getText());
                        boolean isUserNumValid = !TextUtils.isEmpty(mEtNum.getText());
                        return isUserNameValid && isUserNumValid;

                    }

                }).subscribe(aBoolean -> mBtConfirm.setEnabled(aBoolean));
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
    public void cetShowValidateCode(CETGradeBean cetGradeBean) {
        if (cetGradeBean.img_url != null) {
            Glide.with(this).load(cetGradeBean.img_url).into(mIvCode);
        } else {
            Glide.with(this).load(cetGradeBean.url).into(mIvCode);
        }
    }

    @Override
    public void cetShowGrade(CETGradeBean cetGradeBean) {
        if (cetGradeBean.code == 0) {
            mAnimatorSet.start();
            mTvAudition.setText(cetGradeBean.result.hearing);
            mTvRead.setText(cetGradeBean.result.reading);
            mTvComposition.setText(cetGradeBean.result.writing);
            mTvTotal.setText(cetGradeBean.result.total);
            mTvTongue .setText("0");
        } else if (cetGradeBean.code == 80001) {
            cetFailure("输入验证码");
            mLlCode.setVisibility(View.VISIBLE);
            cetShowValidateCode(cetGradeBean);
        } else if (cetGradeBean.code == 1011) {
            cetFailure("验证码有误");
            mPresenter.cetSetValidateCode(mEtNum.getText().toString()); //重新请求验证码
        } else {
            cetFailure("信息有误");
            mPresenter.cetSetValidateCode(mEtNum.getText().toString()); //重新请求验证码
        }
    }

    @Override
    public void cetFailure(String str) {
        Toast.makeText(Utils.getContext(), str, Toast.LENGTH_SHORT).show();
        mPresenter.cetSetValidateCode(mEtNum.getText().toString()); //重新请求验证码
    }

    @Override
    public void setPresenter(@NonNull CETGradeContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

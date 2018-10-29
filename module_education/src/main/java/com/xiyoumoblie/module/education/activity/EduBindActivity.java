package com.xiyoumoblie.module.education.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.lib.common.utils.Installation;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.EduBind.LoginBean;
import com.xiyoumoblie.module.education.mvp.contract.EduBingContract;
import com.xiyoumoblie.module.education.mvp.presenter.EduBingPresenter;
import com.xiyoumoblie.module.education.mvp.source.EduBingModel;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.functions.Function3;

@Route(path = "/education/EduBind")
public class EduBindActivity extends BaseActivity implements EduBingContract.View {

    private Toolbar mToolbar;
    private EduBingContract.Presenter mPresenter;

    private String equipmentId;

    private MyTextView mTvTitle;
    private EditText mEtAcc;
    private EditText mEtPwd;
    private EditText mEtCode;
    private ImageView mIvCode;
    private Button mBtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);
        new EduBingPresenter(new EduBingModel(), this);

        initView();
        clickJudge();
        equipmentId = Installation.id(this);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.frame_title);
        mEtAcc = findViewById(R.id.et_acc);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtCode = findViewById(R.id.et_code);
        mIvCode = findViewById(R.id.iv_code);
        mBtConfirm = findViewById(R.id.bt_confirm);
    }
    private void clickJudge() {
        Observable<CharSequence> accObservable = RxTextView.textChanges(mEtAcc).skip(0);
        Observable<CharSequence> pwdObservable = RxTextView.textChanges(mEtPwd).skip(1);
        Observable<CharSequence> codeObservable = RxTextView.textChanges(mEtCode).skip(1);

        Observable.combineLatest(accObservable, pwdObservable,
                codeObservable,
                new Function3<CharSequence, CharSequence, CharSequence,Boolean>() {

                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2,
                                         CharSequence charSequence3) throws Exception {


                        boolean isUserAccValid = !TextUtils.isEmpty(mEtAcc.getText());
                        boolean isUserPwdValid = !TextUtils.isEmpty(mEtPwd.getText());
                        boolean isUserCodeValid = !TextUtils.isEmpty(mEtCode.getText());
                        return isUserAccValid && isUserPwdValid && isUserCodeValid ;

                    }

                }).subscribe(aBoolean -> mBtConfirm.setEnabled(aBoolean));
    }
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
        Date date = new Date();
       mPresenter.eduSetPublicKey(date.getTime());
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }


    @Override
    public void setPresenter(EduBingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void eduShowValidateCode(String url) {

    }

    @Override
    public void eduFailure(String url) {

    }

    @Override
    public void eduLogin(LoginBean loginBean) {

    }
}

package com.xiyoumobile.module.library.l_common.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.l_common.presenter.LoginPresenter;
import com.xiyoumobile.module.library.l_common.presenter.contract.LoginContract;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumobile.module.library.util.RxBus;
import com.xiyoumoblie.lib.common.base.BaseActivity;

/**
 * 暂时的登录界面
 */
public class LyLoginActivity extends BaseActivity implements LoginContract.View {

    private static final String TAG = "LyLoginActivity";

    LoginContract.Presenter mPresenter;

    private Toolbar mToolbar;
    private EditText mEtAcc;
    private EditText mEtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        mPresenter = new LoginPresenter(Injection.provideRepository(), this);
        initView();

    }

    private void initView() {
        mToolbar = findViewById(R.id.home_tool_bar);
        mEtAcc = findViewById(R.id.et_acc);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtAcc.setText(mPresenter.getId());
        mEtPwd.setText(mPresenter.getPwd());

        setupToolBar(mToolbar, true);

    }

    public void onClickLogin(View view) {
        String id = mEtAcc.getText().toString();
        String pwd = mEtPwd.getText().toString();
        mPresenter.login(id, pwd);
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {

    }

    @Override
    public void onLoginFail() {
    }

    @Override
    public void onLoginSucc() {
        RxBus.getInstance().post(true);
        finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

}

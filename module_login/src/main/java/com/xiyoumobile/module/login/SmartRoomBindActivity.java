package com.xiyoumobile.module.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiyoumobile.module.login.presenter.SmartRoomPresenter;
import com.xiyoumobile.module.login.presenter.view.SmartRoomView;
import com.xiyoumoblie.lib.common.base.BaseMvpActivity;
import com.xiyoumoblie.lib.common.utils.Utils;

public class SmartRoomBindActivity extends BaseMvpActivity<SmartRoomPresenter> implements SmartRoomView {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private TextView mTvFrameTitle;

    private EditText mEtAcc;
    private EditText mEtPwd;
    private EditText mEtCode;
    private ImageView mImCheckCode;

    @Override
    protected void initMvp() {
        mPresenter = new SmartRoomPresenter();
        mPresenter.mView = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("绑定智慧教室");

        mTvFrameTitle = findViewById(R.id.frame_title);
        mTvFrameTitle.setText("智慧教室登录");
        mEtAcc = findViewById(R.id.et_acc);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtCode = findViewById(R.id.et_code);
        mImCheckCode = findViewById(R.id.im_check_code);

    }

    public void onClickChangeCode(View view) {
        mPresenter.getCode();
    }

    public void onClickLogin(View view) {
        String acc = mEtAcc.getText().toString();
        String pwd = mEtPwd.getText().toString();
        String code = mEtCode.getText().toString();
        mPresenter.login(acc, pwd, code);
    }

    @Override
    public void onGetCode(Bitmap bitmap) {
        mImCheckCode.setImageBitmap(bitmap);
    }

    @Override
    public void onGetCodeFail() {
        Toast.makeText(Utils.getContext(), "获取验证码失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail(String msg) {
        Toast.makeText(Utils.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSucceed(String name) {
        Toast.makeText(Utils.getContext(), name, Toast.LENGTH_SHORT).show();
    }

}

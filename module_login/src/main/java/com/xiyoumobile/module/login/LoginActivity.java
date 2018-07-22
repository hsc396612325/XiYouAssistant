package com.xiyoumobile.module.login;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;

@Route(path = "/login/main")
public class LoginActivity extends BaseActivity {

    private RelativeLayout mBtLoginId;
    private RelativeLayout mBtLoginWechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtLoginId = findViewById(R.id.bt_login_id);
        mBtLoginWechat = findViewById(R.id.bt_login_wechat);

    }
}


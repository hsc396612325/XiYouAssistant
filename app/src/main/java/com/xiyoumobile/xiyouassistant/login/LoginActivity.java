package com.xiyoumobile.xiyouassistant.login;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.xiyoumobile.xiyouassistant.R;

public class LoginActivity extends AppCompatActivity {

    private Button mBtLoginId;
    private Button mBtLoginWechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtLoginId = findViewById(R.id.bt_login_id);
        mBtLoginWechat = findViewById(R.id.bt_login_wechat);

        Drawable user = getResources().getDrawable(R.drawable.user);
        user.setBounds(30, 5, 80, 55);
        mBtLoginId.setCompoundDrawables(user, null, null, null);

        Drawable wechat = getResources().getDrawable(R.drawable.wechat);
        wechat.setBounds(30, 5, 80, 55);
        mBtLoginWechat.setCompoundDrawables(wechat, null, null, null);

    }
}

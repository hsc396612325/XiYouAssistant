package com.xiyoumobile.module.login.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.xiyoumobile.module.login.data.LoginBackBean;
import com.xiyoumobile.module.login.data.LoginInfoBean;
import com.xiyoumobile.module.login.net.SmartRoomApi;
import com.xiyoumobile.module.login.presenter.view.SmartRoomView;
import com.xiyoumoblie.lib.common.net.RetrofitFactory;
import com.xiyoumoblie.lib.common.presenter.BasePresenter;

import org.json.JSONObject;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SmartRoomPresenter extends BasePresenter<SmartRoomView> {

    private static final String TAG = "SmartRoomPresenter";

    public Disposable getCode() {
        return RetrofitFactory.INSTANCE.smartRoomCreate(SmartRoomApi.class).getCode(new Date().getTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    if (responseBody != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
                        if (bitmap != null) {
                            mView.onGetCode(bitmap);
                            return;
                        }
                    }
                    mView.onGetCodeFail();
                });
    }

    public Disposable login(String acc, String pwd, String code) {
        String json =  new Gson().toJson(new LoginInfoBean(acc, pwd, code));
        Log.d(TAG, "login: " + json);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return RetrofitFactory.INSTANCE.create(SmartRoomApi.class).login(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    String jsonData = responseBody.string();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    boolean isSucceed = jsonObject.getBoolean("IsSucceed");
                    String msg = jsonObject.getString("Msg");
                    if (isSucceed) {
                        LoginBackBean loginBackBean = new Gson().fromJson(jsonData, LoginBackBean.class);
                        mView.onLoginSucceed(loginBackBean.getObj().getNAME());
                    }  else {
                        mView.onLoginFail(msg);
                    }

                    Log.d(TAG, "login: " + responseBody.string());
                });
    }
}

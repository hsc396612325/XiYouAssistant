package com.xiyoumoblie.module.education.mvp.source;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.xiyoumoblie.lib.common.net.RetrofitFactory;
import com.xiyoumoblie.module.education.bean.EduBind.EdUserBean;
import com.xiyoumoblie.module.education.bean.EduBind.EduPublicKey;
import com.xiyoumoblie.module.education.bean.EduBind.LoginBean;
import com.xiyoumoblie.module.education.net.EducationApi;
import com.xiyoumoblie.module.education.util.Tool;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by heshu on 2018/10/16.
 */

public class EduBingModel {


    public Observable<ResponseBody> eduPublicKey(long time) {



        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .EduGetPublicKey()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<ResponseBody, ObservableSource<ResponseBody>>() {
                    @Override
                    public ObservableSource<ResponseBody> apply(ResponseBody bean) throws Exception {


                        Log.d("2222222", "eduPublicKey: " +bean.string());
                        return Observable.create(e -> e.onNext(bean));
                    }
                });


    }


//    public Observable<LoginBean> eduLogin(EdUserBean edUserBean) {
//        return RetrofitFactory.INSTANCE.create(EducationApi.class)
//                .EduLogin(edUserBean.getStudentNum(),edUserBean.getPassword(),edUserBean.getValidateCode(),edUserBean.getEquipmentId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .flatMap(new Function<LoginBean, ObservableSource<LoginBean>>() {
//                    @Override
//                    public ObservableSource<LoginBean> apply(LoginBean bean) throws Exception {
//                        return Observable.create(e -> e.onNext(bean));
//                    }
//                });
//    }
}

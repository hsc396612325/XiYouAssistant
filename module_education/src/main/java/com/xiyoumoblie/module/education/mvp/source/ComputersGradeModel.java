package com.xiyoumoblie.module.education.mvp.source;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.xiyoumoblie.lib.common.net.RetrofitFactory;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;
import com.xiyoumoblie.module.education.net.EducationApi;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by heshu on 2018/10/16.
 */

public class ComputersGradeModel {

    public Observable<CgTimes> cgTimes() {

        return  RetrofitFactory.INSTANCE.create(EducationApi.class)
                .cgGetTimes()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }


    public Observable<Bitmap> cgValidateCode() {

        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .cgGetCode()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<ResponseBody, ObservableSource<Bitmap>>() {
                    @Override
                    public ObservableSource<Bitmap> apply(ResponseBody responseBody) throws Exception {

                        Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
                        return Observable.create(e -> e.onNext(bitmap));
                    }
                });

    }


    public Observable<CgQuery> cgGrade(CgDemandDate cgDemandDate) {

        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .cgGetQuery(cgDemandDate.getDate(),cgDemandDate.getType(),cgDemandDate.getNum(),cgDemandDate.getName(),cgDemandDate.getCode())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}

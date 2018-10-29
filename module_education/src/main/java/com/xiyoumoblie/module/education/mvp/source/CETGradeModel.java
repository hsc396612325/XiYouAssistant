package com.xiyoumoblie.module.education.mvp.source;

import android.util.Log;

import com.xiyoumoblie.lib.common.net.RetrofitFactory;
import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.net.EducationApi;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heshu on 2018/10/16.
 */

public class CETGradeModel {

    public Observable<CETGradeBean> cetGrade(String name, String num, String code) {
        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .CETGetQuery(name,num,code,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    public Observable<CETGradeBean> cetValidateCode(String num) {

        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .CETGetCode("http://www.wexcampus.com/cet/change-img?number="+num+"&token=a8d29bc2d6aca74f65028632e4e869ae")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());


    }
}

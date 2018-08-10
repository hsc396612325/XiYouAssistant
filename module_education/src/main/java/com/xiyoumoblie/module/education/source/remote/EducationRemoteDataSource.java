package com.xiyoumoblie.module.education.source.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.xiyoumoblie.lib.common.net.RetrofitFactory;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;
import com.xiyoumoblie.module.education.source.EducationDataSource;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 远程获取数据的实现
 */

public class EducationRemoteDataSource implements EducationDataSource {
    private static EducationRemoteDataSource INSTANCE;
    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;


    // Prevent direct instantiation.
    private EducationRemoteDataSource() {

    }

    public static EducationRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EducationRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<CgTimes> cgTimes() {

        return  RetrofitFactory.INSTANCE.create(EducationApi.class)
                .cgGetTimes()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<CgTimes, ObservableSource<CgTimes>>() {
                    @Override
                    public ObservableSource<CgTimes> apply(CgTimes cgTimes) throws Exception {
                        return Observable.create(e -> e.onNext(cgTimes));
                    }
                });
    }


    @Override
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


    @Override
    public Observable<CgQuery> cgGrade(CgDemandDate cgDemandDate) {

        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .cgGetQuery(cgDemandDate.getDate(),cgDemandDate.getType(),cgDemandDate.getNum(),cgDemandDate.getName(),cgDemandDate.getCode())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<CgQuery, ObservableSource<CgQuery>>() {
                    @Override
                    public ObservableSource<CgQuery> apply(CgQuery cgGuery) throws Exception {
                        return Observable.create(e -> e.onNext(cgGuery));
                    }
                });
    }

    @Override
    public Observable<CETGradeBean> CETGrade(String name, String num, String code) {
        Log.d("M-->", "CETGrade: "+ name + num + code);
        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .CETGetQuery(name,num,code,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<CETGradeBean, ObservableSource<CETGradeBean>>() {
                    @Override
                    public ObservableSource<CETGradeBean> apply(CETGradeBean cetGradeBean) throws Exception {
                        return Observable.create(e -> e.onNext(cetGradeBean));
                    }
                });
    }

    @Override
    public Observable<CETGradeBean> CETValidateCode(String num) {

        Log.d("M-->", "CETCode: "+num);
        return RetrofitFactory.INSTANCE.create(EducationApi.class)
                .CETGetCode("http://www.wexcampus.com/cet/change-img?number="+num+"&token=a8d29bc2d6aca74f65028632e4e869ae")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<CETGradeBean, ObservableSource<CETGradeBean>>() {
                    @Override
                    public ObservableSource<CETGradeBean> apply(CETGradeBean cetGradeBean) throws Exception {
                        return Observable.create(e -> e.onNext(cetGradeBean));
                    }
                });
    }
}

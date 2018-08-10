package com.xiyoumoblie.module.education.source.local;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;
import com.xiyoumoblie.module.education.source.EducationDataSource;

import io.reactivex.Observable;

/**
 * 本地获取数据的实现
 */

public class EducationLocalDataSource implements EducationDataSource {
    @Nullable
    private static EducationLocalDataSource INSTANCE;

    // Prevent direct instantiation.
    private EducationLocalDataSource(@NonNull Context context) {

    }


    public static EducationLocalDataSource getInstance( @NonNull Context context) {

        if (INSTANCE == null) {
            INSTANCE = new EducationLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<CgTimes> cgTimes() {
        return null;
    }

    @Override
    public Observable<Bitmap> cgValidateCode() {
        return null;
    }

    @Override
    public Observable<CgQuery> cgGrade(CgDemandDate cgDemandDate) {
        return null;
    }

    @Override
    public Observable<CETGradeBean> CETGrade(String name, String num, String codee) {
        return null;
    }

    @Override
    public Observable<CETGradeBean> CETValidateCode(String num){
        return null;
    }
}

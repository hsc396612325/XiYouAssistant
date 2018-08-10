package com.xiyoumoblie.module.education.source;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;

import io.reactivex.Observable;

/**
 * 数据的获取
 *
 * 为了简单起见，有本地获取数据和服务器获得数据两种方法，当本地数据库不存在或者为空使，才使用远程数据源
 */

public class EducationRepository implements EducationDataSource {
    @NonNull
    private final EducationDataSource mTasksRemoteDataSource;

    @NonNull
    private final EducationDataSource mTasksLocalDataSource;

    //单例模式
    private EducationRepository(@NonNull EducationDataSource tasksRemoteDataSource,
                            @NonNull EducationDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    @Nullable
    private static EducationRepository INSTANCE = null;
    public static EducationRepository getInstance(@NonNull EducationDataSource tasksRemoteDataSource,
                                              @NonNull EducationDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new EducationRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<CgTimes> cgTimes() {
        return mTasksRemoteDataSource.cgTimes();
    }

    @Override
    public Observable<Bitmap> cgValidateCode() {
        return mTasksRemoteDataSource.cgValidateCode();
    }

    @Override
    public Observable<CgQuery>  cgGrade(CgDemandDate cgDemandDate) {
        return mTasksRemoteDataSource. cgGrade(cgDemandDate);
    }

    @Override
    public Observable<CETGradeBean> CETGrade(String name, String num, String codee) {
        return mTasksRemoteDataSource.CETGrade(name,num,codee);
    }

    @Override
    public Observable<CETGradeBean> CETValidateCode(String num){
        return mTasksRemoteDataSource.CETValidateCode(num);
    }
}

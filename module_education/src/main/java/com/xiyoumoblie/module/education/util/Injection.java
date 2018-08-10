package com.xiyoumoblie.module.education.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.xiyoumoblie.module.education.source.EducationRepository;
import com.xiyoumoblie.module.education.source.local.EducationLocalDataSource;
import com.xiyoumoblie.module.education.source.remote.EducationRemoteDataSource;

/**
 * Created by heshu on 2018/8/8.
 */

public class Injection {
    public static EducationRepository provideTasksRepository(@NonNull Context context) {

        return EducationRepository.getInstance(
                EducationRemoteDataSource.getInstance(),
                EducationLocalDataSource.getInstance(context));
    }

}

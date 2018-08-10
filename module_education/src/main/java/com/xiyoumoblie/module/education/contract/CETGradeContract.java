package com.xiyoumoblie.module.education.contract;

import android.graphics.Bitmap;

import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.util.BasePresenter;
import com.xiyoumoblie.module.education.util.BaseView;

/**
 * Created by heshu on 2018/8/10.
 */

public interface CETGradeContract {

    interface View extends BaseView<CETGradeContract.Presenter> {

        void cetShowValidateCode(CETGradeBean cetGradeBean); //展示验证码

        void cetShowGrade(CETGradeBean cetGradeBean); //展示成绩

        void cetFailure(String str); //请求失败
    }

    interface Presenter extends BasePresenter {
        void cetSetValidateCode(String num); //请求验证码

        void cetSetGrade(String name,String num,String code); //请求成绩
    }
}

package com.xiyoumoblie.module.education.mvp.contract;

import android.graphics.Bitmap;

import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;
import com.xiyoumoblie.module.education.mvp.base.BasePresenter;
import com.xiyoumoblie.module.education.mvp.base.BaseView;

/**
 * Created by heshu on 2018/8/8.
 */

public interface ComputersGradeContract {

    interface View extends BaseView<Presenter> {
        void cgGetTimes(CgTimes cgTimes);  //展示时间

        void cgShowValidateCode(Bitmap bitmap); //展示验证码

        void cgShowGrade(CgQuery cgQuery); //展示成绩

        void cgFailure(String str); //请求失败
    }

    interface Presenter extends BasePresenter {
        void cgSetValidateCode(); //请求验证码

        void cgSetGrade(CgDemandDate cgDemandDate); //请求成绩
    }
}

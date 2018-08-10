package com.xiyoumoblie.module.education.contract;

import android.graphics.Bitmap;

import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;
import com.xiyoumoblie.module.education.util.BasePresenter;
import com.xiyoumoblie.module.education.util.BaseView;

/**
 * Created by heshu on 2018/8/10.
 */

public interface EduBingContract {
    interface View extends BaseView<Presenter> {

        void ebShowValidateCode(String url); //展示验证码


        void ebFailure(String str); //请求失败
    }

    interface Presenter extends BasePresenter {
        void cgSetValidateCode(String equipmentId); //请求验证码

        void cgSetGrade(String studentNum, String password,String validateCode,String equipmentId); //登录
    }
}

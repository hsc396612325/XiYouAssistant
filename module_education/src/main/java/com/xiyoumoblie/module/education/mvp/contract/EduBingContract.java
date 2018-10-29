package com.xiyoumoblie.module.education.mvp.contract;

import com.xiyoumoblie.module.education.bean.EduBind.EdUserBean;
import com.xiyoumoblie.module.education.bean.EduBind.LoginBean;
import com.xiyoumoblie.module.education.mvp.base.BasePresenter;
import com.xiyoumoblie.module.education.mvp.base.BaseView;

/**
 * Created by heshu on 2018/8/10.
 */

public interface EduBingContract {
    interface View extends BaseView<Presenter> {

        void eduShowValidateCode(String url); //展示验证码

        void eduLogin(LoginBean loginBean);
        void eduFailure(String url); //请求失败
    }

    interface Presenter extends BasePresenter {
        void eduSetPublicKey(long time); //请求publicKey
        void eduLogin(EdUserBean edUserBean); //登录
    }
}

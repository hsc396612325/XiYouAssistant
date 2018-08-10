package com.xiyoumoblie.module.education.source;

import android.graphics.Bitmap;

import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgDemandDate;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;

import io.reactivex.Observable;

/**
 * M的接口
 */

public interface EducationDataSource {

    //计算机等级考试
    Observable<CgTimes> cgTimes();

    Observable<Bitmap> cgValidateCode();

    Observable<CgQuery> cgGrade(CgDemandDate cgDemandDate);

    //四六级
    Observable<CETGradeBean> CETGrade(String name, String num, String codee);

    Observable<CETGradeBean> CETValidateCode(String num);

    //教务登录

}

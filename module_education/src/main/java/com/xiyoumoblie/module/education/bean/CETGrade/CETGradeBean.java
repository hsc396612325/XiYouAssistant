package com.xiyoumoblie.module.education.bean.CETGrade;

import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;

/**
 * Created by heshu on 2018/8/10.
 */

public class CETGradeBean {
    public int code;

    public String message;
    public Result result;
    public String img_url;
    public String url;

    public class Result {

        public String total;
        public String hearing;
        public String reading;
        public String writing;
        public String school;
        public String number;
        public String name;
    }
}

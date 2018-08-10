package com.xiyoumoblie.module.education.source.remote;

import com.xiyoumoblie.module.education.adapter.TimetableAddWeekAdapter;
import com.xiyoumoblie.module.education.bean.CETGrade.CETGradeBean;
import com.xiyoumoblie.module.education.bean.computersGrade.CgQuery;
import com.xiyoumoblie.module.education.bean.computersGrade.CgTimes;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by heshu on 2018/8/8.
 */

public interface EducationApi {

    @GET(EducationUrls.COMPUTERS_VALIDATE_CODE)
    Observable<ResponseBody> cgGetCode();

    @GET(EducationUrls.COMPUTERS_GET_TIMES)
    Observable<CgTimes> cgGetTimes();

    @POST(EducationUrls.COMPUTERS_GET_QUERY )
    @FormUrlEncoded
    Observable<CgQuery> cgGetQuery(@Field("time") String time, @Field("type")String type, @Field("zjh")String zjh, @Field("name")String name, @Field("validateCode")String validateCode);

    @POST(EducationUrls.CET_GET_GRADE )
    @FormUrlEncoded
    Observable<CETGradeBean> CETGetQuery(@Field("name")String name, @Field("number")String number, @Field("img_code")String validateCode, @Field("type")String type);

    @GET
    Observable<CETGradeBean> CETGetCode(@Url String url);

}

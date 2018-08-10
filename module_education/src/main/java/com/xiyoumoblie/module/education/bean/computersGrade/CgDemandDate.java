package com.xiyoumoblie.module.education.bean.computersGrade;

/**
 * Created by heshu on 2018/8/9.
 */

public class CgDemandDate {
    private String date;
    private String type;
    private String name;
    private String num;
    private String code;

    public CgDemandDate( String date,String type,String name,String num, String code){
        this.date = date;
        this.type = type;
        this.name = name;
        this.num = num;
        this.code = code;

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

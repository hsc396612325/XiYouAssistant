package com.xiyoumoblie.module.education.bean.timetable;

/**
 * Created by heshu on 2018/8/7.
 */

public class DateBean {
    private String date;
    private String week;
    private boolean flage;


    public DateBean(String week , String date, boolean flage){
        this.date = date;
        this.week = week;
        this.flage = flage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

}

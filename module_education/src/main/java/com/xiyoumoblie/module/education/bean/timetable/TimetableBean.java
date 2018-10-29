package com.xiyoumoblie.module.education.bean.timetable;



/**
 * Created by heshu on 2018/7/30.
 */

public class TimetableBean implements Comparable<TimetableBean>{
    private String course;
    private String classroom;
    private boolean exist;
    private int colour;
    private int weekNum;
    private int dateNO;

    public TimetableBean(boolean exist){
        this.exist = exist;
    }
    public TimetableBean(boolean exist,String course, String classroom,int colour,int weekNum,int date){
        this.exist = exist;
        this.course = course;
        this.classroom = classroom;
        this.colour = colour;
        this.weekNum = weekNum;
        this.dateNO = date;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weeknum) {
        this.weekNum = weeknum;
    }

    public int getDateNO() {
        return dateNO;
    }

    public void setDateNO(int dateNO) {
        this.dateNO = dateNO;
    }

    @Override
    public int compareTo(TimetableBean timetableBean) {
        // TODO Auto-generated method stub
        if(this.dateNO != timetableBean.dateNO)
            return this.dateNO>timetableBean.dateNO? 1:-1;
        else
            return this.weekNum>timetableBean.weekNum? 1:-1;
    }
}

package com.xiyoumoblie.module.education;

import java.io.Serializable;

/**
 * Created by heshu on 2018/7/24.
 */

public class LessonInfoBean implements Serializable {
    private String date;
    private String weekday;
    private String lesson;
    private String classroom;
    private String course;
    private String event;

    public LessonInfoBean(String date, String weekday, String lesson, String classroom, String event,String course) {
        this.date = date;
        this.weekday = weekday;
        this.lesson = lesson;
        this.classroom = classroom;
        this.event = event;
        this.course = course;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getWeekday () {
        return weekday;
    }

    public void setWeekday (String weekday) {
        this.weekday = weekday;
    }

    public String getLesson () {
        return lesson;
    }

    public void setLesson (String lesson) {
        this.lesson = lesson;
    }

    public String getClassroom () {
        return classroom;
    }

    public void setClassroom (String classroom) {
        this.classroom = classroom;
    }

    public String getEvent () {
        return event;
    }

    public void setEvent (String event) {
        this.event = event;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

package com.xiyoumoblie.module.education.bean.finalGrade;

public class FinalGradeBean {
    private String name;
    private String type;
    private String credit;
    private String peacetime;
    private String testpaper;
    private String total_points;
    private String gpa;

    public FinalGradeBean() {
    }

    public FinalGradeBean(String name, String type, String credit, String peacetime, String testpaper, String total_points, String gpa) {
        this.name = name;
        this.type = type;
        this.credit = credit;
        this.peacetime = peacetime;
        this.testpaper = testpaper;
        this.total_points = total_points;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPeacetime() {
        return peacetime;
    }

    public void setPeacetime(String peacetime) {
        this.peacetime = peacetime;
    }

    public String getTestpaper() {
        return testpaper;
    }

    public void setTestpaper(String testpaper) {
        this.testpaper = testpaper;
    }

    public String getTotal_points() {
        return total_points;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
}

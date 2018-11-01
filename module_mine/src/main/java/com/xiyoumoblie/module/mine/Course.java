package com.xiyoumoblie.module.mine;

public class Course {

    private String name;

    private String id;

    private String credit;

    private String gradePoint;

    private String assessmentMethod;

    public Course(String name, String id, String credit, String gradePoint, String assessmentMethod) {
        this.name = name;
        this.id = id;
        this.credit = credit;
        this.gradePoint = gradePoint;
        this.assessmentMethod = assessmentMethod;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCredit() {
        return credit;
    }

    public String getGradePoint() {
        return gradePoint;
    }

    public String getAssessmentMethod() {
        return assessmentMethod;
    }

}

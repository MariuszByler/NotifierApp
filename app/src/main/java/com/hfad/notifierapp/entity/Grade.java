package com.hfad.notifierapp.entity;

public class Grade {
    private int number;
    private int grade;
    private String subject;
    private String type;
    private int indexNo;
    private String teacherName;
    private String teacherSurname;

    public Grade() {
    }

    public Grade(int number,int grade, String subject, String type, int indexNo, String teacherName, String teacherSurname) {
        this.number = number;
        this.grade = grade;
        this.subject = subject;
        this.type = type;
        this.indexNo = indexNo;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }
}

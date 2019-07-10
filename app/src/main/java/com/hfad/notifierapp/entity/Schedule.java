package com.hfad.notifierapp.entity;

public class Schedule {
    private int number;
    private String subject;
    private int teacherNumber;
    private String groupNumber;
    private String oldDate;
    private String newDate;
    private String time;

    public Schedule() {
    }

    public Schedule(int number, String subject, int teacherNumber, String groupNumber, String oldDate, String newDate, String time) {
        this.number = number;
        this.subject = subject;
        this.teacherNumber = teacherNumber;
        this.groupNumber = groupNumber;
        this.oldDate = oldDate;
        this.newDate = newDate;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getOldDate() {
        return oldDate;
    }

    public void setOldDate(String oldDate) {
        this.oldDate = oldDate;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

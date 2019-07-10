package com.hfad.notifierapp.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {

    private int number;
    private String name;
    private String surname;
    private String email;
    private String text;
    private String date;


    public Message(int number, String name, String surname, String email, String text, String date) {
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.text = text;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


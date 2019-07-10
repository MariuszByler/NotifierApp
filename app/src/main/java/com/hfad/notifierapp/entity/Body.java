package com.hfad.notifierapp.entity;

public class Body {
    private String message;
    private int number;

    public Body() {
    }

    public Body(String message, int number) {
        this.message = message;
        this.number = number;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

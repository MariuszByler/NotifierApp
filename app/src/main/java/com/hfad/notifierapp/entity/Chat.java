package com.hfad.notifierapp.entity;

import java.util.ArrayList;

public class Chat {
    private int number;
    private String senderEmail;
    private String receiverEmail;
    private String senderName;
    private String receiverName;
    private String senderSurname;
    private String receiverSurname;
    private String groupNumber;
    private ArrayList<Message> messages;

    public Chat() {
    }

    public Chat(int number, String senderEmail, String receiverEmail, String senderName, String receiverName, String senderSurname, String receiverSurname, String groupNumber, ArrayList<Message> messages) {
        this.number = number;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderSurname = senderSurname;
        this.receiverSurname = receiverSurname;
        this.groupNumber = groupNumber;
        this.messages = messages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderSurname() {
        return senderSurname;
    }

    public void setSenderSurname(String senderSurname) {
        this.senderSurname = senderSurname;
    }

    public String getReceiverSurname() {
        return receiverSurname;
    }

    public void setReceiverSurname(String receiverSurname) {
        this.receiverSurname = receiverSurname;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }


}


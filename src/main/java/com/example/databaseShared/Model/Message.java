package com.example.databaseShared.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    private String senderLogin;
    private String recipientLogin;
    private String comment;
    private Date date;
    private Boolean read;


    public Message() {}

    public Message(String id, String senderLogin, String recipientLogin, String comment, Date date, Boolean read) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.comment = comment;
        this.date = date;
        this.read = read;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getRecipientLogin() {
        return recipientLogin;
    }

    public void setRecipientLogin(String recipientLogin) {
        this.recipientLogin = recipientLogin;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}


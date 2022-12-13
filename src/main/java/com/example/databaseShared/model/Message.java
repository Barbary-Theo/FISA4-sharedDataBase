package com.example.databaseShared.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Messages")
public class Message {

    @Id
    private String _id;
    private String text;

    public Message() { }

    public Message(String _id, String text) {
        this._id = _id;
        this.text = text;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
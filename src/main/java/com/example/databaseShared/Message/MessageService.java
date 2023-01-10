package com.example.databaseShared.Message;


import java.util.List;

public interface MessageService {

    public List<Message> findAll();
    public Message findOne(String id);
    public void save(Message message);
    public void delete(Message message);

}

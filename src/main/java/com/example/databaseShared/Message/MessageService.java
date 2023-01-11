package com.example.databaseShared.Message;

import java.util.List;

public interface MessageService {

    public List<Message> findAll();
    public List<Message> findById(String id);
    public List<Message> findConversation(String loginOne, String loginTwo);
    public List<Message> findMessageNotReadInConversation(String loginReader, String loginUserConversation);
    public List<Message> findByRecipientLoginAndStatus(String login, String status);
    public void save(Message message);
    public void delete(Message message);

}

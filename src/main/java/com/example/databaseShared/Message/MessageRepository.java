package com.example.databaseShared.Message;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, Long> {

    public List<Message> findById(String id);
    public List<Message> findBySenderLoginAndRecipientLogin(String loginOne, String loginTwo);
    public List<Message> findBySenderLoginAndRecipientLoginAndStatus(String loginReader, String loginUserConversation, String status);
    public List<Message> findByRecipientLoginAndStatus(String login, String status);

}

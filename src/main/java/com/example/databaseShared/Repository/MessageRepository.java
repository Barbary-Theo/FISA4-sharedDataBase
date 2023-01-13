package com.example.databaseShared.Repository;

import com.example.databaseShared.Model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, Long> {

    public List<Message> findById(String id);
    public List<Message> findBySenderLoginAndRecipientLogin(String loginOne, String loginTwo);
    public List<Message> findBySenderLoginAndRecipientLoginAndRead(String loginReader, String loginUserConversation, Boolean status);
    public List<Message> findByRecipientLoginAndRead(String login, Boolean status);

}

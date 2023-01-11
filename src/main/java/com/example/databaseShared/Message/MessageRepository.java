package com.example.databaseShared.Message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, Long> {

    public List<Message> findById(String id);
    @Query("SELECT message FROM messages message WHERE senderLogin = :loginOne OR senderLogin = :loginTwo")
    public List<Message> findConversation(@Param("loginOne") String loginOne, @Param("loginTwo") String loginTwo);

}

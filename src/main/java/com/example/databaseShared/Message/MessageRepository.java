package com.example.databaseShared.Message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends MongoRepository<Message, Long> {

    @Query("SELECT message FROM messages message WHERE message.id = :checkId")
    public Message findById(@Param("checkId") String checkId);

}

package com.example.databaseShared.Message;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, Long> {

    public List<Message> findById(String id);

}

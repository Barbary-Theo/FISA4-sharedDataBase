package com.example.databaseShared.repositories;

import com.example.databaseShared.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {

}
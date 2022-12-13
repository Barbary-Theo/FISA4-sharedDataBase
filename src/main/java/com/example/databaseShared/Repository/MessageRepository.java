package com.example.databaseShared.Repository;

import com.example.databaseShared.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{_id:'?0'}")
    Message findEleById(String _id);

    long count();

}
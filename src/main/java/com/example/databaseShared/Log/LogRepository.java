package com.example.databaseShared.Log;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogRepository extends MongoRepository<Log, Long> {

    public List<Log> findByUserLoginContaining(String login);

}

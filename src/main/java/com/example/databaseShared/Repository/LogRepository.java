package com.example.databaseShared.Repository;

import com.example.databaseShared.Model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogRepository extends MongoRepository<Log, Long> {

    public List<Log> findByUserLoginContaining(String login);

}

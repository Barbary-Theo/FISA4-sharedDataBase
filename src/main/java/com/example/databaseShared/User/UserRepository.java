package com.example.databaseShared.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    public List<User> findByLogin(String login);
    public List<User> findByDescription(String description);

}

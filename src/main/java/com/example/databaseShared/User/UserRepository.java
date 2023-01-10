package com.example.databaseShared.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<User, Long> {

    @Query("SELECT user FROM users user WHERE user.id = :checkId")
    public User findById(@Param("checkId") String checkId);

}

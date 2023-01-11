package com.example.databaseShared.Publication;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PublicationRepository extends MongoRepository<Publication, Long> {
    
    public List<Publication> findById(String id);
    public List<Publication> findByUserLogin(String login);

}

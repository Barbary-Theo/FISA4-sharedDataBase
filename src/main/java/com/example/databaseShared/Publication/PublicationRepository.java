package com.example.databaseShared.Publication;

import com.example.databaseShared.Message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublicationRepository extends MongoRepository<Publication, Long> {

    @Query("SELECT publication FROM publications publication WHERE publication.id = :checkId")
    public Publication findById(@Param("checkId") String checkId);

}

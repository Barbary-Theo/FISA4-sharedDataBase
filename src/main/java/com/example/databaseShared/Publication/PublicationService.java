package com.example.databaseShared.Publication;


import com.example.databaseShared.Message.Message;

import java.util.List;

public interface PublicationService {

    public List<Publication> findAll();
    public Publication findOne(String id);
    public void save(Publication publication);
    public void delete(Publication publication);

}

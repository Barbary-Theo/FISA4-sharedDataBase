package com.example.databaseShared.Publication;

import java.util.List;

public interface PublicationService {

    public List<Publication> findAll();
    public List<Publication> findById(String id);
    public void save(Publication publication);
    public void delete(Publication publication);

}

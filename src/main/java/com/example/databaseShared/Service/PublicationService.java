package com.example.databaseShared.Service;

import com.example.databaseShared.Model.Publication;

import java.util.List;

public interface PublicationService {

    public List<Publication> findAll();
    public List<Publication> findById(String id);
    public List<Publication> findByUserLogin(String userLogin);
    public List<Publication> findByUserLoginInOrderByPublicationDateDesc(List<String> logins);
    public void save(Publication publication);
    public void delete(Publication publication);

}

package com.example.databaseShared.Publication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public List<Publication> findById(String id) {
        return publicationRepository.findById(id);
    }

    @Override
    public List<Publication> findByUserLogin(String userLogin) {
        return publicationRepository.findByUserLogin(userLogin);
    }

    @Override
    public List<Publication> findByUserLoginInOrderByPublicationDateDesc(List<String> logins) {
        return publicationRepository.findByUserLoginInOrderByPublicationDateDesc(logins);
    }

    @Override
    public void save(Publication publication) {
        publicationRepository.save(publication);
    }

    @Override
    public void delete(Publication publication) {
        publicationRepository.delete(publication);
    }
}

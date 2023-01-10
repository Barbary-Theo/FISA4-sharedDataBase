package com.example.databaseShared.Publication;

import com.example.databaseShared.Message.Message;
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
    public Publication findOne(String id) {
        return publicationRepository.findById(id);
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

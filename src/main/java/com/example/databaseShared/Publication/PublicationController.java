package com.example.databaseShared.Publication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PublicationService publicationService;

    @GetMapping("/all")
    public List<Publication> getAllPublications() {
        LOGGER.info("Find all publications");
        return publicationService.findAll();
    }

    @GetMapping("/one/{id}")
    public Publication getOnePublication(@PathVariable("id") String id) {
        LOGGER.info("Find publication by id : " + id);
        List<Publication> publications = publicationService.findById(id);
        return publications != null && !publications.isEmpty() ? publications.get(0) : null;
    }

}

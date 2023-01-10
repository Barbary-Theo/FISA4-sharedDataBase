package com.example.databaseShared.Publication;

import com.example.databaseShared.Message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @GetMapping("/all")
    public List<Publication> getAllPublications() {
        return publicationService.findAll();
    }

    @GetMapping("/one/{id}")
    public Publication getOnePublication(@PathVariable("id") String id) {
        return publicationService.findOne(id);
    }

}

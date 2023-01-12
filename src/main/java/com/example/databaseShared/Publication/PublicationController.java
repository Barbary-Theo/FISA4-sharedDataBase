package com.example.databaseShared.Publication;

import com.example.databaseShared.Log.Log;
import com.example.databaseShared.Log.LogService;
import com.example.databaseShared.User.User;
import com.example.databaseShared.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publications")
public class PublicationController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PublicationService publicationService;
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @Autowired
    private PublicationRepository publicationRepository;

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

    @PostMapping("/addPost")
    public @ResponseBody ResponseEntity<String> addPost(@RequestBody Publication publication) {
        LOGGER.info("Add publication by user : " + publication.getUserLogin() + ", that says : " + publication.getComment());

        try {
            User user = userService.findByLogin(publication.getUserLogin());
            if(user == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found by his login");

            publication.setPublicationDate(new Date());
            publicationService.save(publication);

            List<String> userImplicated = new ArrayList<>();
            userImplicated.add(publication.getUserLogin());
            logService.save(
                    new Log(
                            userImplicated,
                            publication.getUserLogin() + " add publication that contains " + publication.getComment(),
                            new Date()
                    )
            );

            return ResponseEntity.ok("Publication saved");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all/{login}")
    public List<Publication> getAllPublicationLogin(@PathVariable("login") String login) {
        LOGGER.info("Find all publication for user " + login);
        return publicationService.findByUserLogin(login);
    }

    @GetMapping("/actu/{login}")
    public List<Publication> getActuByLogin(@PathVariable("login") String login) {
        LOGGER.info("Find actu for user " + login);
        List<User> friends = userService.findUserFriendsByLogin(login);

        if(friends != null) {
            List<String> friendsLogin = friends.stream().map(User::getLogin).collect(Collectors.toList());
            return publicationService.findByUserLoginInOrderByPublicationDateDesc(friendsLogin);
        }

        return new ArrayList<>();
    }

}

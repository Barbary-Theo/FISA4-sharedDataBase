package com.example.databaseShared.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        LOGGER.info("Find all users");
        return userService.findAll();
    }

    @GetMapping("/all/{page}")
    public List<User> getAllUsers(@PathVariable("page") Integer page) {
        LOGGER.info("Find all users on page : " + page);
        try { return userService.findAll(PageRequest.of(page, 30)); }
        catch (Exception e) { return new ArrayList<>(); }
    }

    @GetMapping("/one/{id}")
    public User getOneUser(@PathVariable("id") String id) {
        LOGGER.info("Find user by id : " + id);
        return userService.findById(id);
    }

    @GetMapping("/login/{login}")
    public User getOneUserByLogin(@PathVariable("login") String login) {
        LOGGER.info("Find user by login : " + login);
        return userService.findByLogin(login);
    }

    @GetMapping("/description/{description}")
    public User getOneUserByDescription(@PathVariable("description") String description) {
        LOGGER.info("Find user by description : " + description);
        return userService.findByDescription(description);
    }

    @GetMapping("/friends/{login}")
    public List<User> getUserFriendsByLogin(@PathVariable("login") String login) {
        LOGGER.info("Find user friends by login : " + login);
        return userService.findUserFriendsByLogin(login);
    }

}

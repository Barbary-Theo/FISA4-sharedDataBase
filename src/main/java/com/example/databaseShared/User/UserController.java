package com.example.databaseShared.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/one/{id}")
    public User getOneUser(@PathVariable("id") String id) {
        LOGGER.info("Find user by id : " + id);
        List<User> users = userService.findById(id);
        return users != null && !users.isEmpty() ? users.get(0) : null;
    }

}

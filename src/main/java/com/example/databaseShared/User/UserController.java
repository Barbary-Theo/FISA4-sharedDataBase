package com.example.databaseShared.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<User> getAllUsers(@RequestBody User user) {
        LOGGER.info("save user : " + user.getId() + ", " + user.getLogin());
        try {
            if(user.getLogin() == null) return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(null);
            User userByLogin = userService.findByLogin(user.getLogin());
            if(userByLogin != null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            userService.save(user);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) { return ResponseEntity.status(HttpStatus.CONFLICT).body(null); }
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

    @PatchMapping("/add/{loginUser}/{loginContact}")
    public ResponseEntity<String> addFriendToUserByLogin(@PathVariable("loginUser") String loginUser,
                                                      @PathVariable("loginContact") String loginContact) {
        LOGGER.info("Add friend " + loginContact + " to " + loginUser);
        try {
            User userOne = userService.findByLogin(loginUser);
            User userTwo = userService.findByLogin(loginContact);

            if(userOne == null || userTwo == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("One of both user is not found, check logins inputted");

            if(userOne.getContactId() == null) userOne.setContactId(new ArrayList<>());
            if(userTwo.getContactId() == null) userTwo.setContactId(new ArrayList<>());

            if(!userOne.getContactId().contains(userTwo.getId())) userOne.getContactId().add(userTwo.getId());
            if(!userTwo.getContactId().contains(userOne.getId())) userTwo.getContactId().add(userOne.getId());

            userService.save(userOne);
            userService.save(userTwo);

            return ResponseEntity.ok("User " + loginUser + " and " + loginContact + " are now friend");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/remove/{loginUser}/{loginContact}")
    public ResponseEntity<String> removeFriendToUserByLogin(@PathVariable("loginUser") String loginUser,
                                                      @PathVariable("loginContact") String loginContact) {
        LOGGER.info("Remove friend " + loginContact + " to " + loginUser);
        try {
            User userOne = userService.findByLogin(loginUser);
            User userTwo = userService.findByLogin(loginContact);

            if(userOne == null || userTwo == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("One of both user is not found, check logins inputted");

            if(userOne.getContactId() == null) userOne.setContactId(new ArrayList<>());
            if(userTwo.getContactId() == null) userTwo.setContactId(new ArrayList<>());

            userOne.getContactId().remove(userTwo.getId());
            userTwo.getContactId().remove(userOne.getId());

            userService.save(userOne);
            userService.save(userTwo);

            return ResponseEntity.ok("User " + loginUser + " and " + loginContact + " are not friend anymore");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

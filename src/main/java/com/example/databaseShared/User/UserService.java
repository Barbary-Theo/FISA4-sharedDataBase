package com.example.databaseShared.User;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public void save(User user);
    public void delete(User user);
    public List<User> findAll();
    public List<User> findAll(Pageable page);
    public User findByLogin(String login);
    public User findByDescription(String description);
    public List<User> findUserFriendsByLogin(String login);

}

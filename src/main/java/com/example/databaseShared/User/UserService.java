package com.example.databaseShared.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public List<User> findById(String id);
    public void save(User user);
    public void delete(User user);

}

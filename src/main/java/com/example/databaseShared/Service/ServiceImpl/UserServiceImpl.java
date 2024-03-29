package com.example.databaseShared.Service.ServiceImpl;

import com.example.databaseShared.Model.User;
import com.example.databaseShared.Repository.UserRepository;
import com.example.databaseShared.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Pageable page) {
        return  userRepository.findAll(page).getContent();
    }

    @Override
    public User findByLogin(String login) {
        List<User> users = userRepository.findByLogin(login);
        return users != null && !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public User findByDescription(String description) {
        List<User> users = userRepository.findByDescription(description);
        return users != null && !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public List<User> findUserFriendsByLogin(String login) {
        User user = this.findByLogin(login);

        if(user == null || user.getContactId() == null || user.getContactId().isEmpty()) return new ArrayList<>();

        List<User> friends = new ArrayList<>();
        for(String contactLogin : user.getContactId()) {
            User friend = this.findByLogin(contactLogin);
            if(friend != null) friends.add(friend);
        }
        return friends;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

}

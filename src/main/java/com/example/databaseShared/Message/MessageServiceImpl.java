package com.example.databaseShared.Message;

import com.example.databaseShared.User.User;
import com.example.databaseShared.User.UserRepository;
import com.example.databaseShared.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findById(String id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findConversation(String loginOne, String loginTwo) {
        User userOne = userService.findByLogin(loginOne);
        User userTwo = userService.findByLogin(loginTwo);

        if(userOne == null || userTwo == null) return new ArrayList<>();
        //In this case both users exist in db
        List<Message> messageUserOneToUserTwo = messageRepository.findBySenderLoginAndRecipientLogin(loginOne, loginTwo);
        List<Message> messageUserTwoToUserOne = messageRepository.findBySenderLoginAndRecipientLogin(loginTwo, loginOne);
        messageUserOneToUserTwo.addAll(messageUserTwoToUserOne);
        return messageUserOneToUserTwo;
    }

    @Override
    public List<Message> findMessageNotReadInConversation(String loginReader, String loginUserConversation) {
        User userOne = userService.findByLogin(loginReader);
        User userTwo = userService.findByLogin(loginUserConversation);

        if(userOne == null || userTwo == null) return new ArrayList<>();
        //In this case both users exist in db
        return messageRepository.findBySenderLoginAndRecipientLoginAndStatus(loginUserConversation, loginReader, "unread");
    }

    @Override
    public List<Message> findByRecipientLoginAndStatus(String login, String status) {
        return messageRepository.findByRecipientLoginAndStatus(login, status);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }
}

package com.example.databaseShared.Service.ServiceImpl;

import com.example.databaseShared.Model.Message;
import com.example.databaseShared.Repository.MessageRepository;
import com.example.databaseShared.Model.User;
import com.example.databaseShared.Service.MessageService;
import com.example.databaseShared.Service.UserService;
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
        return messageRepository.findBySenderLoginAndRecipientLoginAndRead(loginUserConversation, loginReader, false);
    }

    @Override
    public List<Message> findByRecipientLoginAndRead(String login, Boolean isRead) {
        return messageRepository.findByRecipientLoginAndRead(login, isRead);
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

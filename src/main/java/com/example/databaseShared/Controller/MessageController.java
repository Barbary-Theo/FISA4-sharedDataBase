package com.example.databaseShared.Controller;

import com.example.databaseShared.Repository.MessageRepository;
import com.example.databaseShared.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/all")
    public java.util.List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

}

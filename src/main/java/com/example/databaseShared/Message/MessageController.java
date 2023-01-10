package com.example.databaseShared.Message;

import com.example.databaseShared.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/all")
    public List<Message> getAllMessage() {
        return messageService.findAll();
    }

    @GetMapping("/one/{id}")
    public Message getOneUser(@PathVariable("id") String id) {
        return messageService.findOne(id);
    }

}

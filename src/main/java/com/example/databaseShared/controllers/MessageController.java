package com.example.databaseShared.controllers;

import com.example.databaseShared.repositories.MessageRepository;
import com.example.databaseShared.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping(value = "message/all")
    public java.util.List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    @GetMapping(value = "message/{id}")
    public Optional<Message> getAllMessage(@PathVariable("id") String id) {
        return messageRepository.findById(id);
    }

}

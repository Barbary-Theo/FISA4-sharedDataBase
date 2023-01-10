package com.example.databaseShared.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageService messageService;

    @GetMapping("/all")
    public List<Message> getAllMessage() {
        LOGGER.info("Find all messages");
        return messageService.findAll();
    }

    @GetMapping("/one/{id}")
    public Message getOneMessage(@PathVariable("id") String id) {
        LOGGER.info("Find message by id : " + id);
        List<Message> messages = messageService.findById(id);
        return messages != null && !messages.isEmpty() ? messages.get(0) : null;
    }

}

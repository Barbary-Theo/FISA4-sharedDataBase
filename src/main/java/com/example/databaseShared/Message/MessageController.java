package com.example.databaseShared.Message;

import com.example.databaseShared.User.User;
import com.example.databaseShared.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

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

    @PostMapping("/addMessage")
    public @ResponseBody ResponseEntity<Message> addOneMessage(@RequestBody Message message) {

        try {
            User userSender = userService.findByLogin(message.getSenderLogin());
            User userRecipient = userService.findByLogin(message.getRecipientLogin());

            if(userSender == null || userRecipient == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

            message.setDate(new Date());
            messageService.save(message);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/conversation/{loginOne}/{loginTwo}")
    public @ResponseBody List<Message> addOneMessage(@PathVariable("loginOne") String loginOne,
                                                               @PathVariable("loginTwo") String loginTwo) {
        return messageService.findConversation(loginOne, loginTwo);
    }


}

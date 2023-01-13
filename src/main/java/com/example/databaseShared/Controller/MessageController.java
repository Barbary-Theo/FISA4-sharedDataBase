package com.example.databaseShared.Controller;

import com.example.databaseShared.Service.MessageService;
import com.example.databaseShared.Model.Log;
import com.example.databaseShared.Model.Message;
import com.example.databaseShared.Service.LogService;
import com.example.databaseShared.Model.User;
import com.example.databaseShared.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    LogService logService;

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
        LOGGER.info("Add message from " + message.getSenderLogin() + " to " + message.getRecipientLogin() + ", that says : " + message.getComment());
        try {
            User userSender = userService.findByLogin(message.getSenderLogin());
            User userRecipient = userService.findByLogin(message.getRecipientLogin());

            if(userSender == null || userRecipient == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

            message.setDate(new Date());
            message.setRead(false);
            messageService.save(message);

            List<String> usersImplicated = new ArrayList<>();
            usersImplicated.add(message.getSenderLogin());
            usersImplicated.add(message.getRecipientLogin());
            logService.save(
                new Log(
                    usersImplicated,
                    "new message send by " + message.getSenderLogin() + " to " + message.getRecipientLogin() + " that contains text " + message.getComment(),
                    new Date()
                    )
            );

            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/conversation/{loginOne}/{loginTwo}")
    public @ResponseBody List<Message> getConversation(@PathVariable("loginOne") String loginOne,
                                                               @PathVariable("loginTwo") String loginTwo) {
        LOGGER.info("Find conversation between " + loginOne + " and " + loginTwo);
        return messageService.findConversation(loginOne, loginTwo);
    }

    @GetMapping("/unread/{login}")
    public @ResponseBody List<Message> getMessageNotReadByUserLogin(@PathVariable("login") String login) {
        LOGGER.info("Find all message unread for user " + login);
        return messageService.findByRecipientLoginAndRead(login, false);
    }

    @PatchMapping("/read/{loginReader}/{loginUserConversation}")
    public ResponseEntity<String> readMessageInConversation(@PathVariable("loginReader") String loginReader,
                                                            @PathVariable("loginUserConversation") String loginUserConversation) {
        LOGGER.info(loginReader + " read message of " + loginUserConversation);
        try {

            List<Message> conversation = messageService.findMessageNotReadInConversation(loginReader, loginUserConversation);

            for(Message messageNotRead : conversation) {
                messageNotRead.setRead(true);
                messageService.save(messageNotRead);
            }

            if(!conversation.isEmpty()) {
                List<String> userImplicated = new ArrayList<>();
                userImplicated.add(loginReader);
                userImplicated.add(loginUserConversation);
                logService.save(
                        new Log(userImplicated,
                                loginReader + " has read " + conversation.size() + " of " + loginUserConversation,
                                new Date()
                        )
                );
            }

            return ResponseEntity.ok(conversation.size() + " messages have been read");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}

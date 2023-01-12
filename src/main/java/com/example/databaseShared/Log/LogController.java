package com.example.databaseShared.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LogService logService;


    @GetMapping("/all")
    public List<Log> getAll() {
        LOGGER.info("Find all log");
        return logService.findAll();
    }

    @GetMapping("/all/{login}")
    public List<Log> getAll(@PathVariable("login") String login) {
        LOGGER.info("Find all by login : " + login);
        return logService.findByUserLogin(login);
    }

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<String> saveLog(@RequestBody Log log) {
        LOGGER.info("save log of " + log.getUserLogin() + ", that says : " + log.getLogInfo());
        try {
            log.setLogDate(new Date());
            logService.save(log);
            return ResponseEntity.ok("Log saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

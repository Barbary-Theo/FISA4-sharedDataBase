package com.example.databaseShared.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public List<Log> findByUserLogin(String login) {
        return logRepository.findByUserLoginContaining(login);
    }

    @Override
    public void save(Log log) {
        logRepository.save(log);
    }

    @Override
    public void delete(Log log) {
        logRepository.delete(log);
    }
}

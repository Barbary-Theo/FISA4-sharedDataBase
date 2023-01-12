package com.example.databaseShared.Service;

import com.example.databaseShared.Model.Log;

import java.util.List;

public interface LogService {

    public List<Log> findAll();
    public List<Log> findByUserLogin(String login);
    public void save(Log log);
    public void delete(Log log);

}

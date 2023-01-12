package com.example.databaseShared.Log;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "logs")
public class Log {

    @Id
    private String id;
    private List<String> userLogin;
    private String logInfo;
    private Date logDate;


    public Log() { }

    public Log(String id, List<String> userLogin, String logInfo, Date logDate) {
        this.id = id;
        this.userLogin = userLogin;
        this.logInfo = logInfo;
        this.logDate = logDate;
    }

    public Log(List<String> userLogin, String logInfo, Date logDate) {
        this.userLogin = userLogin;
        this.logInfo = logInfo;
        this.logDate = logDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(List<String> userLogin) {
        this.userLogin = userLogin;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}

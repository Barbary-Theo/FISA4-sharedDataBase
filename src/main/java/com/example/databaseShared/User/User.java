package com.example.databaseShared.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private Date bornDate;
    private String description;
    @Indexed(unique = true)
    private String login;
    private String password;
    private String avatarPath;
    private List<String> contactId;
    
    public User() { }
    public User(String id, Date bornDate, String description, String login, String password, String avatarPath, List<String> contactId) {
        this.id = id;
        this.bornDate = bornDate;
        this.description = description;
        this.login = login;
        this.password = password;
        this.avatarPath = avatarPath;
        this.contactId = contactId;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public List<String> getContactId() {
        return contactId;
    }

    public void setContactId(List<String> contactId) {
        this.contactId = contactId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

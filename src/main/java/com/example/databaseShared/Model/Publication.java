package com.example.databaseShared.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("publications")
public class Publication {
    
    @Id
    private String id;
    private String userLogin;
    private String comment;
    private Date publicationDate;
    private String parentPublicationId;


    public Publication() { }

    public Publication(String id, String userLogin, String comment, Date publicationDate, String parentPublicationId) {
        this.id = id;
        this.userLogin = userLogin;
        this.comment = comment;
        this.publicationDate = publicationDate;
        this.parentPublicationId = parentPublicationId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getParentPublicationId() {
        return parentPublicationId;
    }

    public void setParentPublicationId(String parentPublicationId) {
        this.parentPublicationId = parentPublicationId;
    }
}

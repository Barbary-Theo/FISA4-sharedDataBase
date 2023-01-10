package com.example.databaseShared.Publication;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("publications")
public class Publication {
    
    @Id
    private String id;
    private String userId;
    private String comment;
    private Date publicationDate;
    private String parentPublicationId;


    public Publication() { }

    public Publication(String id, String userId, String comment, Date publicationDate, String parentPublicationId) {
        this.id = id;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

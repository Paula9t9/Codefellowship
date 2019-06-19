package com.fedyr.Codefellowship;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String body;

    @CreationTimestamp
    Date createdAt;

    @ManyToOne
    CfUser createdBy;

    public Post() {
    }

    public Post(String body, CfUser createdBy) {
        this.body = body;
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public CfUser getCreatedBy() {
        return createdBy;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

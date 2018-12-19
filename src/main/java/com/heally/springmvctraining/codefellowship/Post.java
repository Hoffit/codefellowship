package com.heally.springmvctraining.codefellowship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Post {

    public String getBody() {
        return body;
    }

    /**
     * A unique id for the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String body;

    private Date createdAt;

    /**
     * Default constructor.
     */
    public Post() {
        super();
    }

    public Post(String body, Date createdAt) {
        this.body = body;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }
}

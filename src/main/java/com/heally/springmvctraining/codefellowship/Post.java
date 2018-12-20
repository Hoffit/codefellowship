package com.heally.springmvctraining.codefellowship;

import javax.persistence.*;

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

    private String createdAt;

    /**
     * ApplicationUser this post belongs to.
     */
    @ManyToOne
    private ApplicationUser user;

    /**
     * Default constructor.
     */
    public Post() {
        super();
    }

    /**
     * Constructor.
     * @param body The post.
     * @param createdAt The timestamp of when this post was created.
     * @param user The user to which this post belongs.
     */
    public Post(String body, String createdAt, ApplicationUser user) {
        this.body = body;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    /**
     * Getter.
     * @return The user to which the post belongs.
     */
    public ApplicationUser getApplicationUser() {
        return user;
    }

    /**
     * Setter.
     * @param user The user to which the post belongs.
     */
    public void setApplicationUser(ApplicationUser user) {
        this.user = user;
    }
}

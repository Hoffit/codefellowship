package com.heally.springmvctraining.codefellowship;

import javax.persistence.*;

/**
 * The Post code fellows blog entity.
 */
@Entity
public class Post {

    /**
     * A unique id for the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The post.
     */
    private String body;

    /**
     * The post's creation timestamp.
     */
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

    /**
     * Getter.
     * @return The post's creation timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter.
     * @param createdAt The post's creation timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Getter.
     * @return The post's unique id.
     */
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

    /**
     * Getter.
     * @return The post.
     */
    public String getBody() {
        return body;
    }
}

package com.heally.springmvctraining.codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * The ApplicationUser entity.
 */
@Entity
public class ApplicationUser implements UserDetails {

    /**
     * A unique id for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * User first name.
     */
    private String firstName;

    /**
     * User last name.
     */
    private String lastName;

    /**
     * The user's username.
     */
    @Column(unique = true)
    private String username;

    /**
     * The hashed password.
     */
    private String password;

    /**
     * User bio.
     */
    private String bio;

    /**
     * User date of birth.
     */
    private String dateOfBirth;

    /**
     * A default profile pic shared by all users who don't have their own.
     */
    private final static String defaultProfilePic = "/default_profile.jpg";

    /**
     * A list of posts created and owned by the user.
     */
    @OneToMany
    private final List<Post> posts = new LinkedList<>();

    /**
     * A list of application users that the user is following.
     */
    @OneToMany
    private final List<ApplicationUser> following = new LinkedList<>();

    /**
     * Default constructor.
     */
    public ApplicationUser() {
        super();
    }

    /**
     * Constructor
     * @param firstName User first name.
     * @param lastName User last name.
     * @param userName User username.
     * @param password User password, hashed.
     * @param bio User bio.
     * @param dateOfBirth User date of birth.
     */
    public ApplicationUser(String firstName, String lastName, String userName, String password, String bio, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return
     */
    public static String getDefaultProfilePic() {
        return defaultProfilePic;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Add a post to this users list.
     * @param post The post to add.
     */
    public void addPost(Post post) {
        posts.add(post);
    }

    /**
     * Getter.
     * @return The list of users followed.
     */
    public List<ApplicationUser> getFollowing() {
        return following;
    }

    /**
     * Add a new followed user.
     * @param userToFollow The new user being followed.
     */
    public void addFollowing(ApplicationUser userToFollow) {
        this.following.add(userToFollow);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.username + " (" + this.lastName + ", " + this.firstName + ")";
    }
}

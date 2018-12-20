package com.heally.springmvctraining.codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    /**
     * A unique id for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;

    private String bio;

    private String dateOfBirth;

    private final static String defaultProfilePic = "/default_profile.jpg";

    /**
     * A list of posts created and owned by the user.
     */
    @OneToMany
    private final List<Post> posts = new LinkedList<Post>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static String getDefaultProfilePic() {
        return defaultProfilePic;
    }

    public long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return this.username + " (" + this.lastName + ", " + this.firstName + ")";
    }
}

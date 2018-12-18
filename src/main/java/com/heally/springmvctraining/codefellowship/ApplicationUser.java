package com.heally.springmvctraining.codefellowship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ApplicationUser {

    /**
     * A unique id for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String bio;

    private Date dateOfBirth;

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
    public ApplicationUser(String firstName, String lastName, String userName, String password, String bio, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

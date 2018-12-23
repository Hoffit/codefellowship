package com.heally.springmvctraining.codefellowship;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Swing data repository interface for ApplicationUser object.
 */
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    /**
     * Searches the repository for a user with matching username.
     * @param username The username for which to search.
     * @return The ApplicationUser.
     */
    public Optional<ApplicationUser> findByUsername(String username);
}

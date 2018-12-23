package com.heally.springmvctraining.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Custom Spring user details service for the code fellows blog app.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * A spring repository (JPA) of users.
     */
    @Autowired
    private ApplicationUserRepository userRepository;

    /**
     * Returns the ApplicationUser matching the passed in username, if it exists.
     * @param username The username to find in the repository.
     * @return The ApplicationUser if found.
     * @throws UsernameNotFoundException Exception if not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> optional = userRepository.findByUsername(username);
        if (optional.isPresent()) {
            return optional.get();
        }
        else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }
}

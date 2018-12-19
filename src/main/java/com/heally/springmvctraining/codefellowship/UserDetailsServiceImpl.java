package com.heally.springmvctraining.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * A spring repository (JPA) of users.
     */
    @Autowired
    private ApplicationUserRepository userRepository;

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

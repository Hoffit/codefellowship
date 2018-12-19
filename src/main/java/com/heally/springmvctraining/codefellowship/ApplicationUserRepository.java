package com.heally.springmvctraining.codefellowship;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    public Optional<ApplicationUser> findByUsername(String username);
}

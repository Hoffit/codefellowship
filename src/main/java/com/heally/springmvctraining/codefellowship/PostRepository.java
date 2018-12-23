package com.heally.springmvctraining.codefellowship;

import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository for Post objects.
 */
public interface PostRepository extends CrudRepository<Post, Long> {
}

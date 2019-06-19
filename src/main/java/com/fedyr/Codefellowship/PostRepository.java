package com.fedyr.Codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    Post findByCreatedBy(CfUser user);
}

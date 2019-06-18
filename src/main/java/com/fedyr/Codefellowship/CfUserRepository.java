package com.fedyr.Codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface CfUserRepository extends CrudRepository<CfUser, Long> {

    CfUser findByUsername(String username);
}

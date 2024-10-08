package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository for User entity.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Find user by email
     * @param email
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);

}

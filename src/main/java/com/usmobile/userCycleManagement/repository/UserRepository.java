package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

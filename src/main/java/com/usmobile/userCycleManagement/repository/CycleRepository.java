package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.Cycle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Repository for Cycle entity
 */
public interface CycleRepository extends MongoRepository<Cycle, String> {

    /**
     * Find cycles by userId and mdn
     * @param userId
     * @param mdn
     * @return
     */
    List<Cycle> findByUserIdAndMdn(String userId, String mdn);
}
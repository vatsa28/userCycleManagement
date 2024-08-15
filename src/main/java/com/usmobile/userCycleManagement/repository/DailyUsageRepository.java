package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.DailyUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for DailyUsage entity
 */
public interface DailyUsageRepository extends MongoRepository<DailyUsage, String> {

    /**
     * Find daily usage by userId and mdn from startDate to endDate
     * @param userId
     * @param mdn
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("{ 'userId': ?0, 'mdn': ?1, 'usageDate': { $gte: ?2, $lte: ?3 }}")
    List<DailyUsage> findByUsageDateBetween(String userId, String mdn, Instant startDate, Instant endDate);

}
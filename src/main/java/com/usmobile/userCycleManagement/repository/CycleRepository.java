package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.Cycle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CycleRepository extends MongoRepository<Cycle, String> {

//    @Query("{ 'userId': ?0, 'mdn': ?1 }")
    List<Cycle> findByUserIdAndMdn(String userId, String mdn);

}

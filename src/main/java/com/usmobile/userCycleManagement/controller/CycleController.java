package com.usmobile.userCycleManagement.controller;

import com.usmobile.userCycleManagement.pojo.CycleHistoryResponse;
import com.usmobile.userCycleManagement.pojo.CycleRequest;
import com.usmobile.userCycleManagement.pojo.DailyUsageResponse;
import com.usmobile.userCycleManagement.service.CycleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
public class CycleController {

    @Autowired
    CycleService cycleService;

    /**
     * API to get all the cycle history based on a userId and mdn
     * @param cycleRequest
     * @return ResponseEntity<List<CycleHistoryResponse>>
     */
    @GetMapping("/api/v1/getCycleHistory")
    public ResponseEntity<List<CycleHistoryResponse>> getCycleHistory(@Valid @RequestBody CycleRequest cycleRequest){
        List<CycleHistoryResponse> cycleHistory = cycleService.getCycleHistory(cycleRequest.getUserId(), cycleRequest.getMdn());
        return new ResponseEntity<>(cycleHistory, HttpStatus.OK);
    }

    /**
     * API to get daily usage for the current cycle based on a userId and mdn
     * @param cycleRequest
     * @return ResponseEntity<List<DailyUsageResponse>>
     */
    @GetMapping("/api/v1/getDailyUsage")
    public ResponseEntity<List<DailyUsageResponse>> getDailyUsage(@Valid @RequestBody CycleRequest cycleRequest){
        List<DailyUsageResponse> dailyUsage = cycleService.getDailyUsage(cycleRequest.getUserId(), cycleRequest.getMdn());
        return new ResponseEntity<>(dailyUsage, HttpStatus.OK);
    }
}
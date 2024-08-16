package com.usmobile.userCycleManagement.controller;

import com.usmobile.userCycleManagement.pojo.CycleHistoryResponse;
import com.usmobile.userCycleManagement.pojo.DailyUsageResponse;
import com.usmobile.userCycleManagement.service.CycleService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    CycleService cycleService;

    private static final Logger logger = LoggerFactory.getLogger(CycleController.class);

    @Autowired
    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    /**
     * API to get all the cycle history based on a userId and mdn
     * @param userId
     * @param mdn
     * @return ResponseEntity<List<CycleHistoryResponse>>
     */
    @GetMapping("/api/v1/getCycleHistory")
    public ResponseEntity<List<CycleHistoryResponse>> getCycleHistory(@NotBlank @NotNull @RequestParam String userId, @NotBlank @NotNull @RequestParam String mdn){
        logger.info("IN::CycleController:getCycleHistory()");

        List<CycleHistoryResponse> cycleHistory = cycleService.getCycleHistory(userId, mdn);

        logger.info("OUT::CycleController:getCycleHistory()");
        return new ResponseEntity<>(cycleHistory, HttpStatus.OK);
    }

    /**
     * API to get daily usage for the current cycle based on a userId and mdn
     * @param userId
     * @param mdn
     * @return ResponseEntity<List<DailyUsageResponse>>
     */
    @GetMapping("/api/v1/getDailyUsage")
    public ResponseEntity<List<DailyUsageResponse>> getDailyUsage(@NotBlank @RequestParam String userId, @NotBlank @RequestParam String mdn){
        logger.info("IN::CycleController:getDailyUsage()");

        List<DailyUsageResponse> dailyUsage = cycleService.getDailyUsage(userId, mdn);

        logger.info("OUT::CycleController:getDailyUsage()");
        return new ResponseEntity<>(dailyUsage, HttpStatus.OK);
    }
}
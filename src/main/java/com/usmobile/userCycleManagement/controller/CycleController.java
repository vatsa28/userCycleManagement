package com.usmobile.userCycleManagement.controller;

import com.usmobile.userCycleManagement.entity.Cycle;
import com.usmobile.userCycleManagement.entity.DailyUsage;
import com.usmobile.userCycleManagement.pojo.CycleHistoryResponse;
import com.usmobile.userCycleManagement.pojo.CycleRequest;
import com.usmobile.userCycleManagement.pojo.DailyUsageResponse;
import com.usmobile.userCycleManagement.repository.DailyUsageRepository;
import com.usmobile.userCycleManagement.service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CycleController {

    @Autowired
    CycleService cycleService;

    @GetMapping("/api/v1/getCycleHistory")
    public ResponseEntity<List<CycleHistoryResponse>> getCycleHistory(@RequestBody CycleRequest cycleRequest){
        System.out.println("xyz");
        List<CycleHistoryResponse> cycleHistory = cycleService.getCycleHistory(cycleRequest.getUserId(), cycleRequest.getMdn());
        return new ResponseEntity<>(cycleHistory, HttpStatus.OK);
    }

    @GetMapping("/api/v1/getDailyUsage")
    public ResponseEntity<List<DailyUsageResponse>> getDailyUsage(@RequestBody CycleRequest cycleRequest){
        List<DailyUsageResponse> dailyUsage = cycleService.getDailyUsage(cycleRequest.getUserId(), cycleRequest.getMdn());
        return new ResponseEntity<>(dailyUsage, HttpStatus.OK);
    }
}

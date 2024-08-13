package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.entity.Cycle;
import com.usmobile.userCycleManagement.entity.DailyUsage;
import com.usmobile.userCycleManagement.exception.CyclesNotFoundException;
import com.usmobile.userCycleManagement.pojo.CycleHistoryResponse;
import com.usmobile.userCycleManagement.pojo.DailyUsageResponse;
import com.usmobile.userCycleManagement.repository.CycleRepository;
import com.usmobile.userCycleManagement.repository.DailyUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service for all the Cycle Related operations
 */
@Service
public class CycleService {

    private CycleRepository cycleRepository;

    private DailyUsageRepository dailyUsageRepository;

    @Autowired
    public CycleService(CycleRepository cycleRepository, DailyUsageRepository dailyUsageRepository) {
        this.cycleRepository = cycleRepository;
        this.dailyUsageRepository = dailyUsageRepository;
    }

    public List<Cycle> getAllCycles(){
        return cycleRepository.findAll();
    }

    /**
     * Get all the cycles for a user
     * @param userId
     * @param mdn
     * @return List<Cycle>
     */
    public List<Cycle> getCycles(String userId, String mdn){

        List<Cycle> cycles = cycleRepository.findByUserIdAndMdn(userId, mdn);
        if(cycles.size() == 0){
            throw new CyclesNotFoundException("No cycles are found for this user "+userId+" with this mdn "+mdn);
        }
        return cycles;
    }

    /**
     * Get the daily usage for a user
     * @param userId
     * @param mdn
     * @return List<DailyUsageResponse>
     */
    public List<DailyUsageResponse> getDailyUsage(String userId, String mdn){
        List<Cycle> getCycles = getCycles(userId, mdn);
        Collections.sort(getCycles, (a, b)->a.getStartDate().compareTo(b.getStartDate()));
        Cycle last = getCycles.get(getCycles.size()-1);
        List<DailyUsage> dailyUsage = dailyUsageRepository.findByUsageDateBetween(last.getUserId(), last.getMdn(), last.getStartDate(), last.getEndDate());
        List<DailyUsageResponse> resp = new ArrayList<>();
        for (DailyUsage du : dailyUsage){
            resp.add(new DailyUsageResponse(du.getUsageDate(), du.getUsedInMb()));
        }
        return resp;
    }

    /**
     * Get the cycle history for a user
     * @param userId
     * @param mdn
     * @return List<CycleHistoryResponse>
     */
    public List<CycleHistoryResponse> getCycleHistory(String userId, String mdn){
        List<Cycle> getCycles = getCycles(userId, mdn);
        List<CycleHistoryResponse> resp = new ArrayList<>();
        for (Cycle cycle : getCycles){
            resp.add(new CycleHistoryResponse(cycle.getId(), cycle.getStartDate(), cycle.getEndDate()));
        }
        return resp;
    }

}

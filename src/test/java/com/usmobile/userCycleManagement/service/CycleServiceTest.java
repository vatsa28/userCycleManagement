package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.entity.Cycle;
import com.usmobile.userCycleManagement.entity.DailyUsage;
import com.usmobile.userCycleManagement.exception.CyclesNotFoundException;
import com.usmobile.userCycleManagement.pojo.DailyUsageResponse;
import com.usmobile.userCycleManagement.repository.CycleRepository;
import com.usmobile.userCycleManagement.repository.DailyUsageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.usmobile.userCycleManagement.pojo.CycleHistoryResponse;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CycleServiceTest {

    @Mock
    private CycleRepository cycleRepository;

    @InjectMocks
    private CycleService cycleService;

    @Mock
    private DailyUsageRepository dailyUsageRepository;

    @Test
    void getDailyUsage_Success() {

        String userId = "user123";
        String mdn = "mdn123";
        Cycle cycle = new Cycle(mdn, Instant.parse("2024-05-01T00:00:00.000+00:00"), Instant.parse("2024-05-31T23:59:59.000+00:00"), userId);
        Cycle cycle2 = new Cycle(mdn, Instant.parse("2024-04-01T00:00:00.000+00:00"), Instant.parse("2024-04-30T23:59:59.000+00:00"), userId);

        when(cycleRepository.findByUserIdAndMdn(userId, mdn)).thenReturn(Arrays.asList(cycle, cycle2));
        DailyUsage dailyUsage1 = new DailyUsage(userId, mdn, Instant.parse("2024-05-30T23:30:59.000+00:00"), 500);
        DailyUsage dailyUsage2 = new DailyUsage(userId, mdn, Instant.parse("2024-05-11T22:30:59.000+00:00"), 600);

        when(dailyUsageRepository.findByUsageDateBetween(userId, mdn, Instant.parse("2024-05-01T00:00:00.000+00:00"), Instant.parse("2024-05-31T23:59:59.000+00:00")))
                .thenReturn(Arrays.asList(dailyUsage1, dailyUsage2));

        // Act
        List<DailyUsageResponse> dailyUsageResponses = cycleService.getDailyUsage(userId, mdn);

        // Assert
        assertNotNull(dailyUsageResponses);
        assertEquals(2, dailyUsageResponses.size());
        assertEquals(new DailyUsageResponse(Instant.parse("2024-05-30T23:30:59.000+00:00"), 500), dailyUsageResponses.get(0));
        assertEquals(new DailyUsageResponse(Instant.parse("2024-05-11T22:30:59.000+00:00"), 600), dailyUsageResponses.get(1));
    }

    @Test
    void getDailyUsage_Success_DailyUsage0() {

        String userId = "user123";
        String mdn = "mdn123";
        Cycle cycle = new Cycle(mdn, Instant.parse("2024-05-01T00:00:00.000+00:00"), Instant.parse("2024-05-31T23:59:59.000+00:00"), userId);

        when(cycleRepository.findByUserIdAndMdn(userId, mdn)).thenReturn(Arrays.asList(cycle));

        when(dailyUsageRepository.findByUsageDateBetween(userId, mdn, Instant.parse("2024-05-01T00:00:00.000+00:00"), Instant.parse("2024-05-31T23:59:59.000+00:00")))
                .thenReturn(Arrays.asList());

        // Act
        List<DailyUsageResponse> dailyUsageResponses = cycleService.getDailyUsage(userId, mdn);

        // Assert
        assertNotNull(dailyUsageResponses);
        assertEquals(0, dailyUsageResponses.size());
    }
    @Test
    void getCycleHistory_Success() {
        // Arrange
        String userId = "user123";
        String mdn = "mdn123";
        Cycle cycle1 = new Cycle(mdn, Instant.parse("2024-05-01T00:00:00.000+00:00"), Instant.parse("2024-05-31T00:00:00.000+00:00"), userId);
        cycle1.setId("66b7bcd1bf0da5c93223a70b");
        Cycle cycle2 = new Cycle(mdn, Instant.parse("2024-04-01T00:00:00.000+00:00"), Instant.parse("2024-04-30T00:00:00.000+00:00"), userId);
        cycle2.setId("66b7bcd1bf0da5c93223a70c");

//            when(cycleService.getCycles(userId, mdn)).thenReturn(Arrays.asList(cycle1, cycle2));
        when(cycleRepository.findByUserIdAndMdn(userId, mdn)).thenReturn(Arrays.asList(cycle1, cycle2));
        // Act
        List<CycleHistoryResponse> cycleHistoryResponses = cycleService.getCycleHistory(userId, mdn);

        // Assert
        assertNotNull(cycleHistoryResponses);
        assertEquals(2, cycleHistoryResponses.size());
        assertEquals(new CycleHistoryResponse("66b7bcd1bf0da5c93223a70b", Instant.parse("2024-05-01T00:00:00.000+00:00"), Instant.parse("2024-05-31T00:00:00.000+00:00")), cycleHistoryResponses.get(0));
        assertEquals(new CycleHistoryResponse("66b7bcd1bf0da5c93223a70c", Instant.parse("2024-04-01T00:00:00.000+00:00"), Instant.parse("2024-04-30T00:00:00.000+00:00")), cycleHistoryResponses.get(1));
    }

    @Test
    void getCycleHistory_NoCyclesFound() {
        // Arrange
        String userId = "user123";
        String mdn = "mdn123";

        when(cycleRepository.findByUserIdAndMdn(userId, mdn)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(CyclesNotFoundException.class, () -> cycleService.getCycleHistory(userId, mdn));

    }
}

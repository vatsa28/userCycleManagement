package com.usmobile.userCycleManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usmobile.userCycleManagement.entity.DailyUsage;
import com.usmobile.userCycleManagement.pojo.CreateUserRequest;
import com.usmobile.userCycleManagement.pojo.CycleHistoryResponse;
import com.usmobile.userCycleManagement.pojo.DailyUsageResponse;
import com.usmobile.userCycleManagement.service.CycleService;
import com.usmobile.userCycleManagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CycleController.class)
public class CycleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CycleService cycleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCycleHistory() throws Exception {

        String userId = "64d1a0e6e7f4c56d4b8e9c45";
        String mdn = "1234567890";

        CycleHistoryResponse response1 = new CycleHistoryResponse("66b7bcd1bf0da5c93223a70b", Instant.parse("2024-05-01T00:00:00.000+00:00"), Instant.parse("2024-05-31T00:00:00.000+00:00"));
        CycleHistoryResponse response2 = new CycleHistoryResponse("66b7bcd1bf0da5c93223a70b", Instant.parse("2024-04-01T00:00:00.000+00:00"), Instant.parse("2024-04-30T00:00:00.000+00:00"));

        List<CycleHistoryResponse> cycleHistory = Arrays.asList(response1, response2);

        given(cycleService.getCycleHistory(userId, mdn)).willReturn(cycleHistory);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getCycleHistory")
                        .param("userId", userId)
                        .param("mdn", mdn))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(cycleHistory)))
                        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    public void testNoCycles() throws Exception{
        String userId = "64d1a0e6e7f4c56d4b8e9c45";
        String mdn = "1234567890";

        List<CycleHistoryResponse> cycleHistory = Arrays.asList();
        given(cycleService.getCycleHistory(userId, mdn)).willReturn(cycleHistory);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getCycleHistory")
                        .param("userId", userId)
                        .param("mdn", mdn))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cycleHistory)))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testGetDailyUsage() throws Exception {

        String userId = "64d1a0e6e7f4c56d4b8e9c45";
        String mdn = "1234567890";

        DailyUsageResponse dailyUsage1 = new DailyUsageResponse(Instant.parse("2024-05-30T23:30:59.000+00:00"), 500);
        DailyUsageResponse dailyUsage2 = new DailyUsageResponse(Instant.parse("2024-05-11T22:30:59.000+00:00"), 600);

        List<DailyUsageResponse> dailyUsages = Arrays.asList(dailyUsage1, dailyUsage2);

        given(cycleService.getDailyUsage(userId, mdn)).willReturn(dailyUsages);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getDailyUsage")
                        .param("userId", userId)
                        .param("mdn", mdn))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(dailyUsages)))
                        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testGetDailyUsage0() throws Exception {

        String userId = "64d1a0e6e7f4c56d4b8e9c45";
        String mdn = "1234567890";

        List<DailyUsageResponse> dailyUsages = Arrays.asList();

        given(cycleService.getDailyUsage(userId, mdn)).willReturn(dailyUsages);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getDailyUsage")
                        .param("userId", userId)
                        .param("mdn", mdn))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dailyUsages)))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testGetCycleHistoryUserIdBlank() throws Exception {

        String userId = "";
        String mdn = "1234567890";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getCycleHistory")
                        .param("userId", userId)
                        .param("mdn", mdn))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCycleHistoryMdnBlank() throws Exception {

        String userId = "64d1a0e6e7f4c56d4b8e9c45";
        String mdn = "";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getCycleHistory")
                        .param("userId", userId)
                        .param("mdn", mdn))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetDailyUsageUserIdBlank() throws Exception {

        String userId = "";
        String mdn = "1234567890";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getDailyUsage")
                        .param("userId", userId)
                        .param("mdn", mdn))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetDailyUsageMdnBlank() throws Exception {

        String userId = "64d1a0e6e7f4c56d4b8e9c45";
        String mdn = "";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getDailyUsage")
                        .param("userId", userId)
                        .param("mdn", mdn))
                .andExpect(status().isBadRequest());
    }

}

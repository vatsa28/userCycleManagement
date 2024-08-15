package com.usmobile.userCycleManagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Response POJO to represent the daily usage of a user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyUsageResponse {

    Instant date;

    double dailyUsage;
}
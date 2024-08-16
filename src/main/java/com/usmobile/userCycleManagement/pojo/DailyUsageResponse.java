package com.usmobile.userCycleManagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyUsageResponse that = (DailyUsageResponse) o;
        return Double.compare(that.dailyUsage, dailyUsage) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, dailyUsage);
    }
}
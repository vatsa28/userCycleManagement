package com.usmobile.userCycleManagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

/**
 * Response POJO to represent the cycle history.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CycleHistoryResponse {

    private String cycleId;

    Instant startDate;

    Instant endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CycleHistoryResponse that = (CycleHistoryResponse) o;
        return Objects.equals(cycleId, that.cycleId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cycleId, startDate, endDate);
    }
}
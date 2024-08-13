package com.usmobile.userCycleManagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

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
}
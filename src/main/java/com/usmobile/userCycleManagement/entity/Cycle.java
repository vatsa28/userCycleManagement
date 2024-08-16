package com.usmobile.userCycleManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Entity class for Cycle
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cycle")
public class Cycle {

    @Id
    private String id;
    private String mdn;
    private Instant startDate;
    private Instant endDate;
    private String userId;

    public Cycle(String mdn, Instant startDate, Instant endDate, String userId) {
        this.mdn = mdn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }
}

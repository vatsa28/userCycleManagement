package com.usmobile.userCycleManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Entity class for DailyUsage
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "dailyUsage")
public class DailyUsage {
    @Id
    private String id;
    private String mdn;
    private String userId;
    private Instant usageDate;
    private double usedInMb;

    public DailyUsage(String userId, String mdn, Instant usageDate, double usedInMb) {
        this.userId = userId;
        this.mdn = mdn;
        this.usageDate = usageDate;
        this.usedInMb = usedInMb;
    }
}

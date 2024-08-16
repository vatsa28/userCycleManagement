package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.DailyUsage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class DailyUsageRepoTest {

    @Autowired
    private DailyUsageRepository dailyUsageRepository;

    @Test
    public void testFindByUserIdAndMdn() {

        String userId = "user123";
        String mdn = "1234567890";
        Instant startDate = Instant.parse("2021-01-01T00:00:00Z");
        Instant endDate = Instant.parse("2021-01-31T00:00:00Z");

        DailyUsage dailyUsage1 = new DailyUsage();
        dailyUsage1.setId("1");
        dailyUsage1.setUserId(userId);
        dailyUsage1.setMdn(mdn);
        dailyUsage1.setUsageDate(Instant.parse("2021-01-02T00:00:00Z"));
        dailyUsage1.setUsedInMb(100.75);

        DailyUsage dailyUsage2 = new DailyUsage();
        dailyUsage2.setId("2");
        dailyUsage2.setUserId(userId);
        dailyUsage2.setMdn(mdn);
        dailyUsage2.setUsageDate(Instant.parse("2021-01-05T00:00:00Z"));
        dailyUsage2.setUsedInMb(300.75);

        dailyUsageRepository.save(dailyUsage1);
        dailyUsageRepository.save(dailyUsage2);

        List<DailyUsage> dailyUsages = dailyUsageRepository.findByUsageDateBetween(userId, mdn, startDate, endDate);

        assertThat(dailyUsages).hasSize(2);
        assertThat(dailyUsages.get(0).getUserId()).isEqualTo(userId);
        assertThat(dailyUsages.get(0).getMdn()).isEqualTo(mdn);
        assertThat(dailyUsages.get(0).getUsageDate()).isBetween(startDate, endDate);
        assertThat(dailyUsages.get(0).getUsedInMb()).isEqualTo(100.75);

        assertThat(dailyUsages.get(1).getUserId()).isEqualTo(userId);
        assertThat(dailyUsages.get(1).getMdn()).isEqualTo(mdn);
        assertThat(dailyUsages.get(1).getUsageDate()).isBetween(startDate, endDate);
        assertThat(dailyUsages.get(1).getUsedInMb()).isEqualTo(300.75);
    }

    @Test
    public void testFindByUserIdAndMdn_NoResults() {

        String userId = "nonExistentUser";
        String mdn = "0987654321";
        Instant startDate = Instant.parse("2021-01-01T00:00:00Z");
        Instant endDate = Instant.parse("2021-01-31T00:00:00Z");

        List<DailyUsage> dailyUsages = dailyUsageRepository.findByUsageDateBetween(userId, mdn, startDate, endDate);

        assertThat(dailyUsages).isEmpty();
    }

    @Test
    public void testFindByUserIdAndMdn_NoMdn() {

        String userId = "user123";
        String mdn = "1234567890";
        Instant startDate = Instant.parse("2021-01-01T00:00:00Z");
        Instant endDate = Instant.parse("2021-01-31T00:00:00Z");

        DailyUsage dailyUsage1 = new DailyUsage();
        dailyUsage1.setId("1");
        dailyUsage1.setUserId(userId);
        dailyUsage1.setMdn("0987654321");
        dailyUsage1.setUsageDate(Instant.parse("2021-01-02T00:00:00Z"));
        dailyUsage1.setUsedInMb(100.75);

        DailyUsage dailyUsage2 = new DailyUsage();
        dailyUsage2.setId("2");
        dailyUsage2.setUserId(userId);
        dailyUsage2.setMdn("0987654321");
        dailyUsage2.setUsageDate(Instant.parse("2021-01-05T00:00:00Z"));
        dailyUsage2.setUsedInMb(300.75);

        dailyUsageRepository.save(dailyUsage1);
        dailyUsageRepository.save(dailyUsage2);

        List<DailyUsage> dailyUsages = dailyUsageRepository.findByUsageDateBetween(userId, mdn, startDate, endDate);

        assertThat(dailyUsages).isEmpty();
    }

    @Test
    public void testFindByUserIdAndMdn_NoDateRange() {

        String userId = "user123";
        String mdn = "1234567890";
        Instant startDate = Instant.parse("2021-01-01T00:00:00Z");
        Instant endDate = Instant.parse("2021-01-31T00:00:00Z");

        DailyUsage dailyUsage1 = new DailyUsage();
        dailyUsage1.setId("1");
        dailyUsage1.setUserId(userId);
        dailyUsage1.setMdn(mdn);
        dailyUsage1.setUsageDate(Instant.parse("2021-02-02T00:00:00Z"));
        dailyUsage1.setUsedInMb(100.75);

        DailyUsage dailyUsage2 = new DailyUsage();
        dailyUsage2.setId("2");
        dailyUsage2.setUserId(userId);
        dailyUsage2.setMdn(mdn);
        dailyUsage2.setUsageDate(Instant.parse("2021-02-05T00:00:00Z"));
        dailyUsage2.setUsedInMb(300.75);

        dailyUsageRepository.save(dailyUsage1);
        dailyUsageRepository.save(dailyUsage2);

        List<DailyUsage> dailyUsages = dailyUsageRepository.findByUsageDateBetween(userId, mdn, startDate, endDate);

        assertThat(dailyUsages).isEmpty();
    }

    @Test
    public void testFindByUserIdAndMdn_NoUserId() {

        String userId = "user123";
        String mdn = "1234567890";
        Instant startDate = Instant.parse("2021-01-01T00:00:00Z");
        Instant endDate = Instant.parse("2021-01-31T00:00:00Z");

        DailyUsage dailyUsage1 = new DailyUsage();
        dailyUsage1.setId("1");
        dailyUsage1.setUserId("nonExistentUser");
        dailyUsage1.setMdn(mdn);
        dailyUsage1.setUsageDate(Instant.parse("2021-01-02T00:00:00Z"));
        dailyUsage1.setUsedInMb(100.75);

        DailyUsage dailyUsage2 = new DailyUsage();
        dailyUsage2.setId("2");
        dailyUsage2.setUserId("nonExistentUser");
        dailyUsage2.setMdn(mdn);
        dailyUsage2.setUsageDate(Instant.parse("2021-01-05T00:00:00Z"));
        dailyUsage2.setUsedInMb(300.75);

        dailyUsageRepository.save(dailyUsage1);
        dailyUsageRepository.save(dailyUsage2);

        List<DailyUsage> dailyUsages = dailyUsageRepository.findByUsageDateBetween(userId, mdn, startDate, endDate);

        assertThat(dailyUsages).isEmpty();
    }

}

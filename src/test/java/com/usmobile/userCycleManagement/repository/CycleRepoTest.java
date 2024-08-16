package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.Cycle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CycleRepoTest {
    @Autowired
    private CycleRepository cycleRepository;

    @Test
    public void testFindByUserIdAndMdn() {

        String userId = "66baf15bd61db82db7069a78";
        String mdn = "1234567890";

        Cycle cycle1 = new Cycle();
        cycle1.setId("66b999213b4bedf37204e6ac");
        cycle1.setUserId(userId);
        cycle1.setMdn(mdn);
        cycle1.setStartDate(Instant.parse("2021-01-01T00:00:00Z"));
        cycle1.setEndDate(Instant.parse("2021-01-31T00:00:00Z"));

        Cycle cycle2 = new Cycle();
        cycle2.setId("66b998fe3b4bedf37204e6a0");
        cycle2.setUserId(userId);
        cycle2.setMdn(mdn);
        cycle2.setStartDate(Instant.parse("2021-03-01T00:00:00Z"));
        cycle2.setEndDate(Instant.parse("2021-03-31T00:00:00Z"));

        cycleRepository.save(cycle1);
        cycleRepository.save(cycle2);

        List<Cycle> cycles = cycleRepository.findByUserIdAndMdn(userId, mdn);

        assertThat(cycles).hasSize(2);
        assertThat(cycles.get(0).getUserId()).isEqualTo(userId);
        assertThat(cycles.get(0).getMdn()).isEqualTo(mdn);
    }

    @Test
    public void testFindByUserIdAndMdn_NoResults() {

        String userId = "nonExistentUser";
        String mdn = "0987654321";

        List<Cycle> cycles = cycleRepository.findByUserIdAndMdn(userId, mdn);

        assertThat(cycles).isEmpty();
    }

    @Test
    public void testFindByUserIdAndMdn_NoMdn() {

        String userId = "66baf15bd61db82db7069a78";
        String mdn = "1234567890";

        Cycle cycle1 = new Cycle();
        cycle1.setId("66b999213b4bedf37204e6ac");
        cycle1.setUserId(userId);
        cycle1.setMdn("0987654321");
        cycle1.setStartDate(Instant.parse("2021-01-01T00:00:00Z"));
        cycle1.setEndDate(Instant.parse("2021-01-31T00:00:00Z"));

        Cycle cycle2 = new Cycle();
        cycle2.setId("66b998fe3b4bedf37204e6a0");
        cycle2.setUserId(userId);
        cycle2.setMdn("0987654321");
        cycle2.setStartDate(Instant.parse("2021-03-01T00:00:00Z"));
        cycle2.setEndDate(Instant.parse("2021-03-31T00:00:00Z"));

        cycleRepository.save(cycle1);
        cycleRepository.save(cycle2);

        List<Cycle> cycles = cycleRepository.findByUserIdAndMdn(userId, mdn);

        assertThat(cycles).hasSize(0);
    }

    @Test
    public void testFindByUserIdAndMdn_NoUserId() {

        String userId = "66baf15bd61db82db7069a78";
        String mdn = "1234567890";

        Cycle cycle1 = new Cycle();
        cycle1.setId("66b999213b4bedf37204e6ac");
        cycle1.setUserId("nonExistentUser");
        cycle1.setMdn(mdn);
        cycle1.setStartDate(Instant.parse("2021-01-01T00:00:00Z"));
        cycle1.setEndDate(Instant.parse("2021-01-31T00:00:00Z"));

        Cycle cycle2 = new Cycle();
        cycle2.setId("66b998fe3b4bedf37204e6a0");
        cycle2.setUserId("nonExistentUser");
        cycle2.setMdn(mdn);
        cycle2.setStartDate(Instant.parse("2021-03-01T00:00:00Z"));
        cycle2.setEndDate(Instant.parse("2021-03-31T00:00:00Z"));

        cycleRepository.save(cycle1);
        cycleRepository.save(cycle2);

        List<Cycle> cycles = cycleRepository.findByUserIdAndMdn(userId, mdn);

        assertThat(cycles).hasSize(0);
    }
}

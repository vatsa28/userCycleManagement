package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.Cycle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CycleRepoTest {
    @Autowired
    private CycleRepository cycleRepository;

    @Test
    public void testFindByUserIdAndMdn() {

        String userId = "user123";
        String mdn = "1234567890";

        Cycle cycle1 = new Cycle();
        cycle1.setId("1");
        cycle1.setUserId(userId);
        cycle1.setMdn(mdn);

        Cycle cycle2 = new Cycle();
        cycle2.setId("2");
        cycle2.setUserId(userId);
        cycle2.setMdn(mdn);

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

        String userId = "user123";
        String mdn = "1234567890";

        Cycle cycle1 = new Cycle();
        cycle1.setId("1");
        cycle1.setUserId(userId);
        cycle1.setMdn("0987654321");

        Cycle cycle2 = new Cycle();
        cycle2.setId("2");
        cycle2.setUserId(userId);
        cycle2.setMdn("0987654321");

        cycleRepository.save(cycle1);
        cycleRepository.save(cycle2);

        List<Cycle> cycles = cycleRepository.findByUserIdAndMdn(userId, mdn);

        assertThat(cycles).hasSize(0);
    }

    @Test
    public void testFindByUserIdAndMdn_NoUserId() {

        String userId = "user123";
        String mdn = "1234567890";

        Cycle cycle1 = new Cycle();
        cycle1.setId("1");
        cycle1.setUserId("nonExistentUser");
        cycle1.setMdn(mdn);

        Cycle cycle2 = new Cycle();
        cycle2.setId("2");
        cycle2.setUserId("nonExistentUser");
        cycle2.setMdn(mdn);

        cycleRepository.save(cycle1);
        cycleRepository.save(cycle2);

        List<Cycle> cycles = cycleRepository.findByUserIdAndMdn(userId, mdn);

        assertThat(cycles).hasSize(0);
    }
}

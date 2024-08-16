package com.usmobile.userCycleManagement.repository;

import com.usmobile.userCycleManagement.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserId() {
        String email = "john.doe"+Math.random()+"@gmail.com";
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail(email);
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail(email);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.get().getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testFindByUserIdNotFound() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@gmail.com");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("simple.mail@gmail.com");
        assertThat(foundUser).isEmpty();
    }
}

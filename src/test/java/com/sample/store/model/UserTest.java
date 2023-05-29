package com.sample.store.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.sample.store.type.UserType.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldReturnTrueIfUserIsEmployee() {
        User user = new User("USER001", "abc", EMPLOYEE, LocalDateTime.now());

        boolean isEmployee = user.isEmployee();

        assertTrue(isEmployee);
    }

    @Test
    void shouldReturnFalseIfUserIsNotEmployee() {
        User user = new User("USER001", "abc", OTHERS, LocalDateTime.now());

        boolean isEmployee = user.isEmployee();

        assertFalse(isEmployee);
    }

    @Test
    void shouldReturnTrueIfUserIsAffiliate() {
        User user = new User("USER001", "abc", AFFILIATE, LocalDateTime.now());

        boolean isAffiliate = user.isAffiliate();

        assertTrue(isAffiliate);
    }

    @Test
    void shouldReturnFalseIfUserIsNotAffiliate() {
        User user = new User("USER001", "abc", EMPLOYEE, LocalDateTime.now());

        boolean isAffiliate = user.isAffiliate();

        assertFalse(isAffiliate);
    }

    @Test
    void shouldReturnTrueIfUserIsHasJoined2YearsBack() {
        User user = new User("USER001", "abc", AFFILIATE, LocalDateTime.of(2020, 3, 10, 5, 6));

        boolean hasJoined2YearsBack = user.hasJoinedBack(2);

        assertTrue(hasJoined2YearsBack);
    }

    @Test
    void shouldReturnFalseIfUserIsHasNotJoined2YearsBack() {
        User user = new User("USER001", "abc", AFFILIATE, LocalDateTime.now());

        boolean hasJoined2YearsBack = user.hasJoinedBack(2);

        assertFalse(hasJoined2YearsBack);
    }
}
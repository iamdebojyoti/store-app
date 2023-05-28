package com.awign.store.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldReturnTrueIfUserIsEmployee() {
        User user = new User("abc", UserType.EMPLOYEE, LocalDateTime.now());

        boolean isEmployee = user.isEmployee();

        assertEquals(isEmployee, true);
    }

    @Test
    void shouldReturnFalseIfUserIsNotEmployee() {
        User user = new User("abc", UserType.AFFILIATE, LocalDateTime.now());

        boolean isEmployee = user.isEmployee();

        assertEquals(isEmployee, false);
    }

    @Test
    void shouldReturnTrueIfUserIsAffiliate() {
        User user = new User("abc", UserType.AFFILIATE, LocalDateTime.now());

        boolean isAffiliate = user.isAffiliate();

        assertEquals(isAffiliate, true);
    }

    @Test
    void shouldReturnFalseIfUserIsNotAffiliate() {
        User user = new User("abc", UserType.EMPLOYEE, LocalDateTime.now());

        boolean isAffiliate = user.isAffiliate();

        assertEquals(isAffiliate, false);
    }

    @Test
    void shouldReturnTrueIfUserIsHasJoined2YearsBack() {
        User user = new User("abc", UserType.AFFILIATE, LocalDateTime.of(2020, 3,10, 5, 6));

        boolean hasJoined2YearsBack = user.hasJoinedBack(2);

        assertEquals(hasJoined2YearsBack, true);
    }

    @Test
    void shouldReturnFalseIfUserIsHasNotJoined2YearsBack() {
        User user = new User("abc", UserType.AFFILIATE, LocalDateTime.now());

        boolean hasJoined2YearsBack = user.hasJoinedBack(2);

        assertEquals(hasJoined2YearsBack, false);
    }
}
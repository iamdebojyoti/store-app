package com.awign.store.model;

import com.awign.store.type.UserType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.awign.store.type.UserType.AFFILIATE;
import static com.awign.store.type.UserType.EMPLOYEE;

public class User {

    private final String userId;
    private final String name;
    private final UserType userType;
    private final LocalDateTime joiningDate;

    public User(String userId, String name, UserType userType, LocalDateTime joiningDate) {
        this.userId = userId;
        this.name = name;
        this.userType = userType;
        this.joiningDate = joiningDate;
    }

    public boolean isEmployee() {
        return userType == EMPLOYEE;
    }

    public boolean isAffiliate() {
        return userType == AFFILIATE;
    }

    public boolean hasJoinedBack(int years) {
        long timeSpent = ChronoUnit.YEARS.between(joiningDate, LocalDateTime.now());
        return timeSpent >= years;
    }
}

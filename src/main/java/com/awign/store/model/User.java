package com.awign.store.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class User {

    private String name;
    private UserType userType;
    private LocalDateTime joiningDate;

    public User(String name, UserType userType, LocalDateTime joiningDate) {
        this.name = name;
        this.userType = userType;
        this.joiningDate = joiningDate;
    }

    public boolean isEmployee() {
        return userType == UserType.EMPLOYEE;
    }

    public boolean isAffiliate() {
        return userType == UserType.AFFILIATE;
    }

    public boolean hasJoinedBack(int years) {
        long timeSpent = ChronoUnit.YEARS.between(joiningDate, LocalDateTime.now());
        return timeSpent >= years;
    }
}

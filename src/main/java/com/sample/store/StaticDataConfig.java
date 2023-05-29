package com.sample.store;

import com.sample.store.model.Discount;
import com.sample.store.model.User;
import com.sample.store.type.DiscountType;
import com.sample.store.type.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This config class is used to setup the static data. Normally, we would have put this info in the DB
 */
@Configuration
public class StaticDataConfig {

    @Bean
    public Map<String, Discount> discountMap() {
        Map<String, Discount> discountMap = new HashMap<>();
        discountMap.put("employee-discount", new Discount("employee-discount", 30.0, DiscountType.PERCENTAGE));
        discountMap.put("affiliate-discount", new Discount("affiliate-discount", 10.0, DiscountType.PERCENTAGE));
        discountMap.put("old-customer-discount", new Discount("old-customer-discount", 5.0, DiscountType.PERCENTAGE));
        discountMap.put("pricing-discount", new Discount("pricing-discount", 5.0, DiscountType.FLAT));

        return discountMap;
    }

    @Bean
    public List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User("USER001", "user-1", UserType.EMPLOYEE, LocalDateTime.now()));
        users.add(new User("USER002", "user-2", UserType.OTHERS, LocalDateTime.now()));

        return users;
    }
}

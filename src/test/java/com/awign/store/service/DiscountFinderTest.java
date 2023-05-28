package com.awign.store.service;

import com.awign.store.model.Discount;
import com.awign.store.model.User;
import com.awign.store.type.DiscountType;
import com.awign.store.type.UserType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountFinderTest {

    @Test
    void shouldReturnEmployeeDiscountIfUserIsEmployee() {
        User user = new User("USER001", "abc", UserType.EMPLOYEE, LocalDateTime.now());
        DiscountFinder discountFinder = new DiscountFinder(discountStaticData());

        Discount discount = discountFinder.discountFor(user);

        assertEquals(30.0, discount.getValue());
    }

    @Test
    void shouldReturnAffiliateDiscountIfUserIsAffiliate() {
        User user = new User("USER001", "abc", UserType.AFFILIATE, LocalDateTime.now());
        DiscountFinder discountFinder = new DiscountFinder(discountStaticData());

        Discount discount = discountFinder.discountFor(user);

        assertEquals(10.0, discount.getValue());
    }

    @Test
    void shouldReturnOldCustomerDiscountIfUserIsOlderThan2Years() {
        User user = new User("USER001", "abc", UserType.OTHERS, LocalDateTime.of(2020, 3, 10, 5, 6));
        DiscountFinder discountFinder = new DiscountFinder(discountStaticData());

        Discount discount = discountFinder.discountFor(user);

        assertEquals(5.0, discount.getValue());
    }

    @Test
    void shouldReturnNoDiscountIfUserIsNotOlderThan2YearsAndUserIsInOthersType() {
        User user = new User("USER001", "abc", UserType.OTHERS, LocalDateTime.now());
        DiscountFinder discountFinder = new DiscountFinder(discountStaticData());

        Discount discount = discountFinder.discountFor(user);

        assertEquals(0.0, discount.getValue());
    }

    @Test
    void shouldReturnPricingDiscountWhenPriceIsGt100() {
        DiscountFinder discountFinder = new DiscountFinder(discountStaticData());

        Discount discount = discountFinder.discountFor(300.0);

        assertEquals(15.0, discount.getValue());
    }

    @Test
    void shouldReturn0DiscountIfPriceIsLt100() {
        DiscountFinder discountFinder = new DiscountFinder(discountStaticData());

        Discount discount = discountFinder.discountFor(80.0);

        assertEquals(0.0, discount.getValue());
    }

    Map<String, Discount>  discountStaticData() {
        Map<String, Discount> discountMap = new HashMap<>();
        discountMap.put("employee-discount", new Discount("employee-discount", 30.0, DiscountType.PERCENTAGE));
        discountMap.put("affiliate-discount", new Discount("affiliate-discount", 10.0, DiscountType.PERCENTAGE));
        discountMap.put("old-customer-discount", new Discount("old-customer-discount", 5.0, DiscountType.PERCENTAGE));
        discountMap.put("pricing-discount", new Discount("pricing-discount", 5.0, DiscountType.FLAT));

        return discountMap;
    }
}
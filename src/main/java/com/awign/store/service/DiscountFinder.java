package com.awign.store.service;

import com.awign.store.model.Discount;
import com.awign.store.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DiscountFinder {

    private final Map<String, Discount> discountMap;

    public DiscountFinder(Map<String, Discount> discountMap) {
        this.discountMap = discountMap;
    }

    public Discount discountFor(User user) {
        if (user.isEmployee()) {
            return discountMap.get("employee-discount");
        } else if (user.isAffiliate()) {
            return discountMap.get("affiliate-discount");
        } else if (user.hasJoinedBack(2)) {
            return discountMap.get("old-customer-discount");
        } else {
            return Discount.empty();
        }
    }

    public Discount discountFor(double price) {
        Discount discount = discountMap.get("pricing-discount");
        if (price > 100) {
            int gtVal = (int) (price/100.0);
            return new Discount(discount.getName(), discount.getValue() * gtVal, discount.getType());
        } else {
            return Discount.empty();
        }
    }
}

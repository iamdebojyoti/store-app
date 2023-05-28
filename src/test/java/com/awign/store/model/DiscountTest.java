package com.awign.store.model;

import com.awign.store.type.DiscountType;
import org.junit.jupiter.api.Test;

import static com.awign.store.type.DiscountType.PERCENTAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    @Test
    void shouldReturnDiscountedPriceWhenDiscountInPercentage() {
        Discount discount = new Discount("employee-discount", 10, PERCENTAGE);

        double discountedPrice = discount.apply(120.0);

        assertEquals(108.0, discountedPrice);
    }

    @Test
    void shouldReturnDiscountedPriceWhenDiscountInFlat() {
        Discount discount = new Discount("employee-discount", 10, DiscountType.FLAT);

        double discountedPrice = discount.apply(120.0);

        assertEquals(110.0, discountedPrice);
    }
}
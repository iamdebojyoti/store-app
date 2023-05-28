package com.awign.store.model;

import com.awign.store.type.DiscountType;

public class Discount {

    private final String name;
    private final double value;
    private final DiscountType type;

    public Discount(String name, double value, DiscountType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public DiscountType getType() {
        return type;
    }

    public double apply(double price) {
        if (type == DiscountType.PERCENTAGE) {
            return price - (price * value / 100);
        } else {
            return price - value;
        }
    }

    public static Discount empty() {
        return new Discount("no-discount", 0.0, DiscountType.FLAT);
    }
}

package com.sample.store.model;

import java.util.ArrayList;

public class ItemsList extends ArrayList<Item> {

    public double othersPrice() {
        return this.stream()
                .filter(item -> !item.isGrocery())
                .mapToDouble(Item::totalPrice)
                .sum();
    }

    public double groceryPrice() {
        return this.stream()
                .filter(Item::isGrocery)
                .mapToDouble(Item::totalPrice)
                .sum();
    }
}

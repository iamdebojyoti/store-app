package com.awign.store.model;

import com.awign.store.type.ItemType;

import static com.awign.store.type.ItemType.GROCERY;

public class Item {
    private final String name;
    private final int quantity;
    private final double price;
    private final ItemType itemType;

    public Item(String name, int quantity, double price, ItemType itemType) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.itemType = itemType;
    }

    public double totalPrice() {
        return price * quantity;
    }

    public boolean isGrocery() {
        return itemType == GROCERY;
    }
}

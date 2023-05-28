package com.awign.store.model;

import com.awign.store.type.ItemType;

import static com.awign.store.type.ItemType.GROCERY;

public class Item {
    private String name;
    private int quantity;
    private double price;
    private ItemType itemType;

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

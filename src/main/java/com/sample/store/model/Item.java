package com.sample.store.model;

import com.sample.store.type.ItemType;

import java.util.Objects;

import static com.sample.store.type.ItemType.GROCERY;

public class Item {
    private String name;
    private int quantity;
    private double price;
    private ItemType itemType;

    public Item() {}

    public Item(String name, int quantity, double price, ItemType itemType) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public double totalPrice() {
        return price * quantity;
    }

    public boolean isGrocery() {
        return itemType == GROCERY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity && Double.compare(item.price, price) == 0 && Objects.equals(name, item.name) && itemType == item.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price, itemType);
    }
}

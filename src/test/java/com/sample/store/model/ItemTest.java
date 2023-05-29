package com.sample.store.model;

import com.sample.store.type.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void shouldReturnTotalPriceOfItems() {
        Item item = new Item("potato", 2, 5.6, ItemType.GROCERY);

        double totalPrice = item.totalPrice();

        assertEquals(11.2, totalPrice);
    }

    @Test
    void shouldReturnTrueWhenItemIsGrocery() {
        Item item = new Item("potato", 2, 5.6, ItemType.GROCERY);

        boolean isGrocery = item.isGrocery();

        assertTrue(true);
    }

    @Test
    void shouldReturnFalseWhenItemIsNotGrocery() {
        Item item = new Item("pen", 2, 1.6, ItemType.OTHERS);

        boolean isGrocery = item.isGrocery();

        assertFalse(isGrocery);
    }
}
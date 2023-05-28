package com.awign.store.model;

import com.awign.store.type.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsListTest {

    @Test
    void shouldCalculateGroceryPrice() {
        Item item1 = new Item("potator", 2, 2.0, ItemType.GROCERY);
        Item item2 = new Item("onion", 2, 3.0, ItemType.GROCERY);
        Item item3 = new Item("cabbage", 2, 1.3, ItemType.GROCERY);
        Item item4 = new Item("pen", 2, 4.0, ItemType.OTHERS);
        Item item5 = new Item("pencil", 2, 1.0, ItemType.OTHERS);
        ItemsList itemsList = new ItemsList();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        itemsList.add(item4);
        itemsList.add(item5);

        double groceryprice = itemsList.groceryPrice();

        assertEquals(12.6, groceryprice);
    }

    @Test
    void shouldCalculateOthersPrice() {
        Item item1 = new Item("potator", 2, 2.0, ItemType.GROCERY);
        Item item2 = new Item("onion", 2, 3.0, ItemType.GROCERY);
        Item item3 = new Item("cabbage", 2, 1.3, ItemType.GROCERY);
        Item item4 = new Item("pen", 2, 4.0, ItemType.OTHERS);
        Item item5 = new Item("pencil", 2, 1.0, ItemType.OTHERS);
        ItemsList itemsList = new ItemsList();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        itemsList.add(item4);
        itemsList.add(item5);

        double othersPrice = itemsList.othersPrice();

        assertEquals(10.0, othersPrice);
    }
}
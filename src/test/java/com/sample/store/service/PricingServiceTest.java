package com.sample.store.service;

import com.sample.store.model.Discount;
import com.sample.store.model.Item;
import com.sample.store.model.ItemsList;
import com.sample.store.model.User;
import com.sample.store.type.DiscountType;
import com.sample.store.type.ItemType;
import com.sample.store.type.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

class PricingServiceTest {

    @Test
    void shouldReturnPriceForListOfItems() {
        User user = new User("USER001", "abc", UserType.EMPLOYEE, LocalDateTime.now());
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
        DiscountFinder discountFinder = Mockito.mock(DiscountFinder.class);
        when(discountFinder.discountFor(user)).thenReturn(new Discount("employee-discount", 30.0, DiscountType.PERCENTAGE));
        when(discountFinder.discountFor(18.82)).thenReturn(new Discount("pricing-discount", 5.0, DiscountType.FLAT));

        PricingService pricingService = new PricingService(discountFinder);

        double discountedPrice = pricingService.priceFor(user, itemsList);

        Assertions.assertEquals(13.82, discountedPrice);
    }
}
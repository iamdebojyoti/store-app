package com.sample.store.service;

import com.sample.store.model.Discount;
import com.sample.store.model.ItemsList;
import com.sample.store.model.User;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private final DiscountFinder discountFinder;

    public PricingService(DiscountFinder discountFinder) {
        this.discountFinder = discountFinder;
    }

    public double priceFor(User user, ItemsList items) {
        Discount userDiscount = discountFinder.discountFor(user);
        double groceryPriceAfterDiscount = userDiscount.apply(items.groceryPrice());
        double totalPrice = groceryPriceAfterDiscount + items.othersPrice();

        Discount pricingDiscount = discountFinder.discountFor(totalPrice);

        return pricingDiscount.apply(totalPrice);
    }

}

package com.awign.store.service;

import com.awign.store.model.Discount;
import com.awign.store.model.ItemsList;
import com.awign.store.model.User;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private DiscountFinder discountFinder;

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

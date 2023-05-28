package com.awign.store.controller;

import com.awign.store.model.User;
import com.awign.store.service.PricingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private List<User> users;
    private PricingService pricingService;

    public ItemController(List<User> users, PricingService pricingService) {
        this.users = users;
        this.pricingService = pricingService;
    }

    @PostMapping("/price")
    public ResponseEntity<String> calculatePrice(@RequestBody ItemsRequest itemsRequest) {
        Optional<User> user = users.stream()
                .filter(usr -> usr.getUserId().equals(itemsRequest.getUserId()))
                .findFirst();

        if (user.isPresent()) {
            double price = pricingService.priceFor(user.get(), itemsRequest.getItems());
            return ResponseEntity.status(HttpStatus.OK).body("Price :" + price);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User Id [" + itemsRequest.getUserId() + "] is not present");
        }
    }
}

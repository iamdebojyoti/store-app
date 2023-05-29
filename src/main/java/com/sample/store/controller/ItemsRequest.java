package com.sample.store.controller;

import com.sample.store.model.ItemsList;

public class ItemsRequest {

    private String userId;
    private ItemsList items;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ItemsList getItems() {
        return items;
    }

    public void setItems(ItemsList items) {
        this.items = items;
    }
}

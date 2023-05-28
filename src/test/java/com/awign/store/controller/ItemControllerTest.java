package com.awign.store.controller;

import com.awign.store.model.Item;
import com.awign.store.model.ItemsList;
import com.awign.store.model.User;
import com.awign.store.service.PricingService;
import com.awign.store.type.ItemType;
import com.awign.store.type.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<User> users = new ArrayList<>();
        users.add(new User("USER001", "user-1", UserType.EMPLOYEE, LocalDateTime.now()));
        users.add(new User("USER002", "user-2", UserType.OTHERS, LocalDateTime.now()));
        ItemController itemController = new ItemController(users, pricingService);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    void shouldCalculatePriceWhenUserExists() throws Exception {
        User user = new User("USER001", "abc", UserType.EMPLOYEE, LocalDateTime.now());
        Item item1 = new Item("potato", 2, 2.0, ItemType.GROCERY);
        Item item2 = new Item("onion", 2, 3.0, ItemType.GROCERY);
        Item item3 = new Item("cabbage", 2, 1.3, ItemType.GROCERY);
        Item item4 = new Item("pen", 2, 4.0, ItemType.OTHERS);
        Item item5 = new Item("pencil", 2, 1.0, ItemType.OTHERS);
        ItemsList items = new ItemsList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        ItemsRequest itemsRequest = new ItemsRequest();
        itemsRequest.setUserId("USER001");
        itemsRequest.setItems(items);

        when(pricingService.priceFor(user, items)).thenReturn(30.0);

        MvcResult result = mockMvc.perform(post("/items/price")
                        .content(asJsonString(itemsRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals("Price :30.0", responseContent);
        verify(pricingService, times(1)).priceFor(user, items);
    }

    @Test
    void shouldThrowErrorWhenUserIsNotFound() throws Exception {
        Item item1 = new Item("potator", 2, 2.0, ItemType.GROCERY);
        Item item2 = new Item("onion", 2, 3.0, ItemType.GROCERY);
        Item item3 = new Item("cabbage", 2, 1.3, ItemType.GROCERY);
        Item item4 = new Item("pen", 2, 4.0, ItemType.OTHERS);
        Item item5 = new Item("pencil", 2, 1.0, ItemType.OTHERS);
        ItemsList items = new ItemsList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        ItemsRequest itemsRequest = new ItemsRequest();
        itemsRequest.setUserId("Wrong User");
        itemsRequest.setItems(items);

        MvcResult result = mockMvc.perform(post("/items/price")
                        .content(asJsonString(itemsRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals("User Id [Wrong User] is not present", responseContent);
    }

    // Helper method to convert an object to JSON string
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
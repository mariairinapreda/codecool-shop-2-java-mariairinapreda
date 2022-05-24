package com.codecool.shop.model;

import java.util.List;

public class Cart {
    private List<LineItem> products;

    public Cart(List<LineItem> products) {
        this.products = products;
    }
}

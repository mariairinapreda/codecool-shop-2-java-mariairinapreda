package com.codecool.shop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class Cart {


    private List<LineItem> products;
    private static final Logger logger = LoggerFactory.getLogger(Cart.class);


    private float total;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public List<LineItem> getCart() {
        return products;
    }


    private void getTotalPrice(List<LineItem> items) {
        for (LineItem item : items) {
            this.total += Float.parseFloat(item.getPrice()) * item.getQuantity();
        }
    }

}


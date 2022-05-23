package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

public interface CartDao {
    void add(CartDao cart);
    Product find(int id);
    void remove(int id);
}

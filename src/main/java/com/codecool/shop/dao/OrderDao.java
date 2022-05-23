package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

public interface OrderDao {
    void add(OrderDao order);
    Product find(int id);
    void remove(int id);
}

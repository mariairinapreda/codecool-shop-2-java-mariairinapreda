package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;


public interface OrderDao {
    void add(Order order);
    Product find(int id);
    void remove(int id);
}

package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

public interface UserDao {
    void add(UserDao User);
    UserDao find(int id);
    void remove(int id);
}

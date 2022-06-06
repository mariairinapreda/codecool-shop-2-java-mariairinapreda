package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

public interface UserDao {
    void add(User user);
    UserDao find(int id);
    void remove(int id);
    boolean isLoggedIn(String email);

    void setPaymentMethod(String paypal);
}

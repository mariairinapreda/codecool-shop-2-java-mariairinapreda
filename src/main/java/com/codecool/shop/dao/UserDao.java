package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

public interface UserDao {
    void add(User user);
    User find(int id);
    void remove(int id);
    boolean isLoggedIn(String email);

    void setPaymentMethod(String paypal);
    void updateStatus(String status, int id);
}

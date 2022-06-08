package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private List<User> users = new ArrayList<>();
    private String payment;

    private static UserDaoImpl instance = null;
    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        user.setId(users.size()+1);
        users.add(user);
    }



    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public boolean isLoggedIn(String email) {
        for(User user : users){
            return user.getEmail().equals(email);
        }
        return false;
    }

    @Override
    public void setPaymentMethod(String paymentMethod) {
        this.payment = paymentMethod;

    }

    @Override
    public void updateStatus(String status, int id) {

    }
}

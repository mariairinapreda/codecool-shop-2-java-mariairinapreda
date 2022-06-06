package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class UserDaoDB implements UserDao {
    private final DataSource dataSource;
    private static UserDaoDB instance=null;

    public static UserDaoDB getInstance(DataSource dataSource){
        if(instance==null)instance=new UserDaoDB(dataSource);
        return instance;
    }

    public UserDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public UserDao find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public boolean isLoggedIn(String email) {
        return false;
    }

    @Override
    public void setPaymentMethod(String paypal) {

    }
}

package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
   private List<LineItem> products;
    @Override
    public void add(OrderDao order) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}

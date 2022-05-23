package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.util.List;

public class CartDaoImpl implements CartDao {
    List<ProductDao> products;


    @Override
    public void add(CartDao cart) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}

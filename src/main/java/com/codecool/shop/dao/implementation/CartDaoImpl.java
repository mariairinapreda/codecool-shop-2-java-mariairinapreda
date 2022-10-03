package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private List<LineItem> products;

    private static CartDaoImpl instance = null;

    public CartDaoImpl() {
        this.products = new ArrayList<>();
    }

    public static CartDaoImpl getInstance() {
        if (instance == null) {
            instance = new CartDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(LineItem lineItem) {
        int y = 0;
        for (LineItem product : products) {
            if (product.getProduct().getId() == lineItem.getProduct().getId()) {
                product.setQuantity(product.getQuantity() + 1);
                y += 1;
            }
        }
        if (y == 0) {
            products.add(lineItem);
        }
    }

    @Override
    public Product find(int id) {
        Product product1 = null;
        for (LineItem product : products) {
            if (product.getProduct().getId() == id) product1 = product.getProduct();
        }
        return product1;
    }

    @Override
    public void remove(int id) {
        products.removeIf(product -> product.getProduct().getId() == id);
    }

    @Override
    public List<LineItem> getAll() {
        return products;
    }
}

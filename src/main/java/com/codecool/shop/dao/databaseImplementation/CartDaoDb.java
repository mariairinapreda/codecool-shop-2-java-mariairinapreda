package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.util.List;

public class CartDaoDb implements CartDao {
    private final DataSource dataSource;
    private static CartDaoDb instance=null;

    public static CartDao getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new CartDaoDb(dataSource);
        }
        return instance;
    }

    public CartDaoDb(DataSource dataSource) {
this.dataSource=dataSource;
    }

    @Override
    public void add(LineItem lineItem) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }
}

package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;

public class OrderDaoDB implements OrderDao {
    private final DataSource dataSource;
    private static OrderDaoDB instance=null;

    public static OrderDaoDB getInstance(DataSource dataSource){
        if(instance==null)instance=new OrderDaoDB(dataSource);
        return instance;
    }

    public OrderDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

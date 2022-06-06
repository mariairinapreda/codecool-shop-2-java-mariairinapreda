package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.util.List;

public class ProductDaoDB implements ProductDao {
    private final DataSource dataSource;
    private static ProductDaoDB instance=null;

    public static ProductDaoDB getInstance(DataSource dataSource){
        if(instance==null)instance=new ProductDaoDB(dataSource);
        return instance;
    }

    public ProductDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public Product getByName(String name) {
        return null;
    }
}
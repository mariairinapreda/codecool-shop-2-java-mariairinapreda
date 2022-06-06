package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.util.List;

public class ProductCategoryDaoDB implements ProductCategoryDao {
    private final DataSource dataSource;
    private static ProductCategoryDaoDB instance=null;

    public static ProductCategoryDaoDB getInstance(DataSource dataSource){
        if(instance==null)instance=new ProductCategoryDaoDB(dataSource);
        return  instance;
    }

    public ProductCategoryDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}

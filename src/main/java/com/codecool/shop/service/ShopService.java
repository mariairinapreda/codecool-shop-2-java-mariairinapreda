package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

public class ShopService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
private SupplierDao supplierDao;
private CartDao cartDao;
private OrderDao orderDao;
private UserDao userDao;
private static ShopService instance=null;


    public SupplierDao getSupplierDao() {
        return supplierDao;
    }


    public  static ShopService getInstance(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, CartDao cartDao){
        if(instance==null)instance=new ShopService(productDao, productCategoryDao, supplierDao, cartDao);
        return instance;
    }

    private ShopService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, CartDao cartDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao=supplierDao;
        this.cartDao=cartDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }



    public List<ProductCategory> getAllCategories(){
        return productCategoryDao.getAll();
    }
    public List<Product> getAllProducts(){
        return productDao.getAll();
    }

    public List<Supplier> getAllSuppliers(){
        return supplierDao.getAll();
    }

    public List<LineItem> getAllProdFromCart(){
        List<LineItem> products=new ArrayList<>();
        for (LineItem lineItem : cartDao.getAll()) {
            products.add(lineItem);
        }
        return products;
    }

}

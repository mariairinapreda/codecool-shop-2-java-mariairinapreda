package com.codecool.shop.service;

import com.codecool.shop.config.DatabaseManager;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.databaseImplementation.*;
import com.codecool.shop.dao.implementation.CartDaoImpl;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private CartDao cartDao;
    private UserDao userDao;
    private LineItemDaoDB lineItemDaoDB;
    private static ShopService instance = null;


    public SupplierDao getSupplierDao() {
        return supplierDao;
    }


    public static ShopService getInstance() {
        if (instance == null) instance = new ShopService();
        return instance;
    }

    private ShopService() {

    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public LineItemDaoDB getLineItemDaoDB() {
        return lineItemDaoDB;
    }

    public void setImpl(DaoImplementation daoImplementation) throws SQLException {
        switch (daoImplementation) {
            case IN_MEMORY:
                this.productDao = ProductDaoMem.getInstance();
                this.productCategoryDao = ProductCategoryDaoMem.getInstance();
                this.supplierDao = SupplierDaoMem.getInstance();
                this.cartDao = CartDaoImpl.getInstance();
                break;
            case IN_DATABASE:
                UserInterface ui = new UserInterface(System.in, System.out);
                DatabaseManager databaseManager = new DatabaseManager(ui);
                DataSource dataSource = databaseManager.connect();
                this.productCategoryDao = ProductCategoryDaoDB.getInstance(dataSource);
                this.productDao = ProductDaoDB.getInstance(dataSource, ProductCategoryDaoDB.getInstance(dataSource), SupplierDaoDB.getInstance(dataSource));
                this.supplierDao = SupplierDaoDB.getInstance(dataSource);
                this.userDao = UserDaoDB.getInstance(dataSource);
                this.cartDao = CartDaoImpl.getInstance();
                this.lineItemDaoDB = LineItemDaoDB.getInstance(dataSource, userDao, productDao);
                break;

        }


    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }


    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public List<LineItem> getAllProdFromCart() {
        List<LineItem> products = new ArrayList<>();
        for (LineItem lineItem : cartDao.getAll()) {
            products.add(lineItem);
        }
        return products;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public CartDao getCartDao() {
        return cartDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

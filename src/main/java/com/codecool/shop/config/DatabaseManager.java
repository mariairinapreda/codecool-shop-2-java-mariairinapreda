package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.databaseImplementation.*;
import com.codecool.shop.service.UserInterface;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {

    UserInterface ui;
    CartDao cartDao;
    OrderDao orderDao;
    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    public UserDao userDao;
    private static DatabaseManager instance = null;

    public DatabaseManager(UserInterface ui) {
        this.ui = ui;
    }

    private DatabaseManager() {
        run();
    }

    public static DatabaseManager getInstance() {
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ApplicationProperties properties = new ApplicationProperties();
        dataSource.setDatabaseName(properties.readProperty("database"));
        dataSource.setUser(properties.readProperty("user"));
        dataSource.setPassword(properties.readProperty("password"));

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }

    public void run() {
        try {
            setup();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
        }
    }

    private void setup() throws SQLException {
        DataSource dataSource = connect();
        orderDao=OrderDaoDB.getInstance(dataSource);
        productCategoryDao=ProductCategoryDaoDB.getInstance(dataSource);
        productDao=ProductDaoDB.getInstance(dataSource, ProductCategoryDaoDB.getInstance(dataSource), SupplierDaoDB.getInstance(dataSource));
        supplierDao=SupplierDaoDB.getInstance(dataSource);
        userDao=UserDaoDB.getInstance(dataSource);

    }

}
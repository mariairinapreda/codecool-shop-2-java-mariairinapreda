package com.codecool.shop.config;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.DaoImplementation;
import com.codecool.shop.service.ShopService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ShopService shopService=ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_MEMORY);
        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        shopService.getSupplierDao().add(amazon);
        Supplier apple = new Supplier("Apple", "The latest. Take a look at what’s new, right now.");
        shopService.getSupplierDao().add(apple);
        Supplier huawei= new Supplier("Huawei", "Tablets and phones");
        shopService.getSupplierDao().add(huawei);
        Supplier lenovo = new Supplier("Lenovo", "Computers and other devices");
        shopService.getSupplierDao().add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        shopService.getProductCategoryDao().getAll().add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A cell phone computer, commonly shortened to phone, is portable usually cordless telephone for use in a cellular system.");
        shopService.getProductCategoryDao().getAll().add(phone);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop, laptop computer, or notebook computer is a small, portable personal computer (PC) with a screen and alphanumeric keyboard.");
        shopService.getProductCategoryDao().getAll().add(laptop);


        //setting up products and printing it
        shopService.getProductDao().getAll().add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        shopService.getProductDao().getAll().add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        shopService.getProductDao().getAll().add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        shopService.getProductDao().getAll().add(new Product("IPhone 13", new BigDecimal("799.9"), "USD", "Superbright, Supercolorful, Supersharp.", phone, apple));
        shopService.getProductDao().getAll().add(new Product("tilifon", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", phone, apple));
        shopService.getProductDao().getAll().add(new Product("tilifon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", phone, huawei));
        shopService.getProductDao().getAll().add(new Product("tableta Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, apple));
        shopService.getProductDao().getAll().add(new Product("laptop  a Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", laptop, amazon));
    }

}

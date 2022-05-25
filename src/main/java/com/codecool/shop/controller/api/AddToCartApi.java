package com.codecool.shop.controller.api;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.serialization.LineItemSerialization;
import com.codecool.shop.serialization.Serialization;
import com.codecool.shop.service.ShopService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Adding", urlPatterns = {"/{id}"}, loadOnStartup = 2)
public class AddToCartApi extends HttpServlet {
    private int id;
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private Serialization<Product> serialization = new Serialization();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShopService shopService = ShopService.getInstance(productDao, productCategoryDao, supplierDao);
        PrintWriter writer = response.getWriter();
        List<Product> productList=null;
        productList.add(shopService.getAllProducts().get(id));
        writer.println(serialization.serialization(productList));
    }


}


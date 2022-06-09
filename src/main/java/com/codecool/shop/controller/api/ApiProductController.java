package com.codecool.shop.controller.api;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.serialization.Serialization;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.DaoImplementation;
import com.codecool.shop.service.ShopService;
import com.google.gson.annotations.Expose;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(urlPatterns = {"/api/products"})
public class ApiProductController extends HttpServlet{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private CartDao cartDao;

    private Serialization<Product> serialization = new Serialization();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShopService shopService=ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_MEMORY);
        PrintWriter writer = resp.getWriter();
        List<Product> productList = shopService.getAllProducts();
        writer.println(serialization.serialization(productList));
    }



}

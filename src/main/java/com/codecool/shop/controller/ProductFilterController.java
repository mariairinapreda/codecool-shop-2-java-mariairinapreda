package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.serialization.Serialization;
import com.codecool.shop.service.DaoImplementation;
import com.codecool.shop.service.ShopService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/"})
public class ProductFilterController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    ShopService shopService;
    private Serialization<Product> serialization = new Serialization();

    private void setData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ShopService shopService = ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_DATABASE);
        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        webContext = new WebContext(request, response, request.getServletContext());
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            setData(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(req);
        String parameterCat = req.getParameter("category");
        String parameterSup = req.getParameter("supplier");
        List<Product> productList = shopService.getAllProducts();
        if (parameterCat != null) {
            productList = productList.stream().filter(product -> product.getProductCategory().getName().equals(parameterCat)).collect(Collectors.toList());
        }
        if (parameterSup != null) {
            productList = productList.stream().filter(product -> product.getSupplier().getName().equals(parameterSup)).collect(Collectors.toList());
        }
        PrintWriter writer = resp.getWriter();
        writer.println(serialization.serialization(productList));
    }
}


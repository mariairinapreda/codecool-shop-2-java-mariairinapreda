package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoImpl;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;
import com.codecool.shop.service.DaoImplementation;
import com.codecool.shop.service.ShopService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    ShopService shopService;
    HttpSession session;


    private void setData(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        shopService=ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_DATABASE);
        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        webContext = new WebContext(request, response, request.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            setData(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<LineItem> productList=shopService.getAllProdFromCart();
        int sum=0;
        for (LineItem product : productList) {
            sum+= product.getProduct().getDefaultPrice().intValue() *product.getQuantity();
        }
        int numberOfProd=0;
        for (LineItem lineItem : productList) {
            numberOfProd+=lineItem.getQuantity();
        }
        webContext.setVariable("numberOfProd", numberOfProd);
        webContext.setVariable("totalPrice",sum);
        webContext.setVariable("items", productList);
        templateEngine.process("product/cart.html", webContext, resp.getWriter());
    }
    }

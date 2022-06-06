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
import java.util.List;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    ShopService shopService;
    HttpSession session;


    private void setData(HttpServletRequest request,HttpServletResponse response){
        shopService=ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_MEMORY);
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDao= SupplierDaoMem.getInstance();
//        CartDao cartDao= CartDaoImpl.getInstance();
//        shopService = ShopService.getInstance(productDataStore,productCategoryDataStore, supplierDao,cartDao );

        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        webContext = new WebContext(request, response, request.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        List<LineItem> productList=shopService.getAllProdFromCart();
        int totalSum=0;
        for (LineItem lineItem : productList) {
            totalSum+=lineItem.getPrice();
        }

        webContext.setVariable("items", productList);
        templateEngine.process("product/cart.html", webContext, resp.getWriter());
    }
    }

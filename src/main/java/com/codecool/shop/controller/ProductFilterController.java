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
import com.codecool.shop.model.Product;
import com.codecool.shop.serialization.Serialization;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/"})
public class ProductFilterController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    ShopService shopService;
    private Serialization<Product> serialization = new Serialization();

    private void setData(HttpServletRequest request,HttpServletResponse response){
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao=SupplierDaoMem.getInstance();
        CartDao cartDao= CartDaoImpl.getInstance();
        shopService = ShopService.getInstance(productDataStore,productCategoryDataStore, supplierDao, cartDao);

        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        webContext = new WebContext(request, response, request.getServletContext());
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
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


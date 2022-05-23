package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ShopService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
TemplateEngine templateEngine;
WebContext webContext;
ShopService shopService;
//Cart cart;


    private void setData(HttpServletRequest request,HttpServletResponse response){
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao=SupplierDaoMem.getInstance();
        shopService = ShopService.getInstance(productDataStore,productCategoryDataStore, supplierDao);

        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        webContext = new WebContext(request, response, request.getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      setData(req, resp);
        webContext.setVariable("category", shopService.getProductCategory(1));
        webContext.setVariable("products", shopService.getProductsForCategory(1));
        webContext.setVariable("category", shopService.getProductCategory(2));
        webContext.setVariable("products", shopService.getProductsForCategory(2));
        webContext.setVariable("category", shopService.getProductCategory(3));
        webContext.setVariable("products", shopService.getProductsForCategory(3));
        webContext.setVariable("suppliers", shopService.getSupplierDao().getAll());

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        templateEngine.process("product/index.html", webContext, resp.getWriter());
    }


}

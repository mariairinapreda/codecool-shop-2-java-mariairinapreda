package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
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
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
TemplateEngine templateEngine;
WebContext webContext;
ShopService shopService;
CartDao cart;


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
        String parameterCat = req.getParameter("category");
        String parameterSup = req.getParameter("supplier");
        List<Product> productList = shopService.getAllProducts();
        if(parameterCat != null){
            productList = productList.stream().filter(product -> product.getProductCategory().getName().equals(parameterCat)).collect(Collectors.toList());
        }
        if(parameterSup != null){
            productList  =  productList.stream().filter(product -> product.getSupplier().getName().equals(parameterSup)).collect(Collectors.toList());
        }

        webContext.setVariable("products", productList);
        List<ProductCategory> productCategoryList = shopService.getAllCategories();
        webContext.setVariable("categories", productCategoryList);
        List<Supplier> suppliersList = shopService.getAllSuppliers();
        webContext.setVariable("suppliers", suppliersList);
        webContext.setVariable("category", parameterCat);

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        templateEngine.process("product/index.html", webContext, resp.getWriter());
    }

//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        setData(req, resp);
//        String category = req.getParameter("category");
//        String supplier = req.getParameter("supplier");
//        resp.sendRedirect(req.getContextPath()+"/"+ "?category=" + category + "&supplier=" +supplier);
//    }



}

package com.codecool.shop.controller;


import com.codecool.shop.model.*;
import com.codecool.shop.service.DaoImplementation;
import com.codecool.shop.service.ShopService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/"}, loadOnStartup = 1)
public class ProductController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    ShopService shopService;
    HttpSession httpSession;


    private void setData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        shopService = ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_DATABASE);
        httpSession = request.getSession();
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
        String parameterCat = req.getParameter("category");
        String parameterSup = req.getParameter("supplier");
        List<Product> productList = shopService.getAllProducts();
        if (parameterCat != null) {
            productList = productList.stream().filter(product -> product.getProductCategory().getName().equals(parameterCat)).collect(Collectors.toList());
        }
        if (parameterSup != null) {
            productList = productList.stream().filter(product -> product.getSupplier().getName().equals(parameterSup)).collect(Collectors.toList());
        }
        List<LineItem> productssss = shopService.getCartDao().getAll();
        webContext.setVariable("products", productList);
        List<ProductCategory> productCategoryList = shopService.getAllCategories();
        webContext.setVariable("categories", productCategoryList);
        List<Supplier> suppliersList = shopService.getAllSuppliers();
        webContext.setVariable("suppliers", suppliersList);
        webContext.setVariable("category", parameterCat);
        webContext.setVariable("session", httpSession);


        if (productssss.size() > 0) {
            int numberOfProd = 0;
            for (LineItem product : productssss) {
                numberOfProd += product.getQuantity();
            }
            int sum = 0;
            for (LineItem product : productssss) {
                sum += product.getProduct().getDefaultPrice().intValue();
            }
            webContext.setVariable("totalPrice", sum);
            webContext.setVariable("numberOfProd", numberOfProd);
        }


        templateEngine.process("product/index.html", webContext, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            setData(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");
        resp.sendRedirect(req.getContextPath() + "/" + "?category=" + category + "&supplier=" + supplier);
    }


}

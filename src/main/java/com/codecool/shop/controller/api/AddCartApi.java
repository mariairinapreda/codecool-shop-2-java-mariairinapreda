package com.codecool.shop.controller.api;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoImpl;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.serialization.Serialization;
import com.codecool.shop.service.DaoImplementation;
import com.codecool.shop.service.ShopService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"api/add-to-cart"}, loadOnStartup = 2)
public class AddCartApi extends HttpServlet {

    private ProductDao productDao;
    private CartDao cartDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private Serialization<Product> serialization = new Serialization();
    private Cart cart=new Cart(null);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ShopService shopService = ShopService.getInstance(productDao, productCategoryDao, supplierDao, cartDao);
        ShopService shopService=ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_MEMORY);
        StringBuffer stringBuffer = new StringBuffer();


        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line.charAt(7));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int id= Integer.parseInt(stringBuffer.toString());

        Product product=shopService.getAllProducts().get(id);
        LineItem lineItem=new LineItem(1,product.getPrice(),product);
        cartDao=CartDaoImpl.getInstance();
        cartDao.add(lineItem);
        response.setContentType("application/json");
        String responseData = "{\"result\":\"success\"}";
        PrintWriter out = response.getWriter();
        out.println(responseData);
    }


}


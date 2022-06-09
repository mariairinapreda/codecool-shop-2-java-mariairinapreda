package com.codecool.shop.controller.api;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/api/cart-products"})

public class SaveCartApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShopService shopService=ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_DATABASE);
        StringBuffer stringBuffer = new StringBuffer();


        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line.substring(7,line.length()-2));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
String[] listas=stringBuffer.toString().split(",");
        for (String lista : listas) {
            String[] numbers=lista.split(",");
            int id=Integer.parseInt(numbers[0]);
            int quantity=Integer.parseInt(numbers[1]);


        }

        System.out.println(stringBuffer);
        response.setContentType("application/json");
        String responseData = "{\"result\":\"success\"}";
        PrintWriter out = response.getWriter();
        out.println(responseData);

    }


}

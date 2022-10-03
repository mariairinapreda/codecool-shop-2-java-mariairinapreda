package com.codecool.shop.controller.api;

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
import java.sql.SQLException;

@WebServlet(urlPatterns = {"api/delete-product"}, loadOnStartup = 2)
public class DeleteProductApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShopService shopService = ShopService.getInstance();
        try {
            shopService.setImpl(DaoImplementation.IN_DATABASE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuffer stringB = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                stringB.append(line.charAt(7));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int id = Integer.parseInt(stringB.toString());
        shopService.getCartDao().getAll().removeIf(lineItem -> lineItem.getProduct().getId() == id);
        response.setContentType("application/json");
        String responseData = "{\"result\":\"success\"}";
        PrintWriter out = response.getWriter();
        out.println(responseData);
    }

}

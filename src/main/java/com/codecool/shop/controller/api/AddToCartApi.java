package com.codecool.shop.controller.api;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.serialization.LineItemSerialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Adding", urlPatterns = {"{id}"}, loadOnStartup = 2)
public class AddToCartApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LineItem dao=new LineItem(1, ProductDaoMem.getInstance().fin);
        Gson gson=new GsonBuilder().registerTypeAdapter(LineItem.class, new LineItemSerialization()).serializeNulls().create();
    }


}


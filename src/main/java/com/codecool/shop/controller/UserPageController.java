package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
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
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user-page")
public class UserPageController  extends HttpServlet {

    TemplateEngine templateEngine;
    WebContext webContext;
    HttpSession httpSession;

    private void setData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ShopService shopService=ShopService.getInstance();
        shopService.setImpl(DaoImplementation.IN_DATABASE);
        httpSession = request.getSession();
        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        webContext = new WebContext(request, response, request.getServletContext());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            setData(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        webContext = new WebContext(req, resp, req.getServletContext());
        templateEngine.process("product/userPage.html", webContext,  resp.getWriter());
    }
}

package com.codecool.shop.controller;


import com.codecool.shop.config.DatabaseManager;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController {
    TemplateEngine templateEngine;
    WebContext webContext;
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        webContext = new WebContext(req, resp, req.getServletContext());
        UserDao userdb = DatabaseManager.getInstance().userDao;
        userdb.updateStatus("unsigned", 1);
        resp.sendRedirect(req.getContextPath()+"/");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}

package com.codecool.shop.controller;

import com.codecool.shop.com.mail.Smail;
import com.codecool.shop.config.HashPassword;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserStatus;
import com.codecool.shop.service.ShopService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    HttpSession httpSession;
    ShopService shopService;
    HashPassword hashPassword;


    private void setData(HttpServletRequest request,HttpServletResponse response){
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao= SupplierDaoMem.getInstance();
        CartDao cartDao= CartDaoImpl.getInstance();
        shopService = ShopService.getInstance(productDataStore,productCategoryDataStore, supplierDao, cartDao);
        httpSession = request.getSession();
        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        webContext = new WebContext(request, response, request.getServletContext());
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        webContext = new WebContext(req, resp, req.getServletContext());
        templateEngine.process("product/register.html", webContext,  resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setData(req, resp);
        String userName = req.getParameter("username");
        String pass = req.getParameter("password");
        String password = HashPassword.get_SHA_512_SecurePassword(pass);
//        if(userName!= null){
//            try {
//                new Smail().send(userName);
//                resp.sendRedirect(req.getContextPath()+"/");
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }else{
//            resp.sendRedirect(req.getContextPath()+"/login");
//        }
        UserDao userDao = UserDaoImpl.getInstance();

        if(userDao.isLoggedIn(userName)){
            httpSession.removeAttribute(userName);
            httpSession.setAttribute("signUpError", "This email is already used, try another");
        }else{
            User user = new User("A", userName, password, UserStatus.SIGNED);
            userDao.add(user);
            System.out.println(userDao);
            httpSession.setAttribute("user", user);
            try {
                new Smail().send(userName);
                resp.sendRedirect(req.getContextPath()+"/login");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}

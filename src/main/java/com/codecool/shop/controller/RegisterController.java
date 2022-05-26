package com.codecool.shop.controller;

import com.codecool.shop.com.mail.Smail;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoImpl;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    HttpSession httpSession;
    ShopService shopService;


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
        String userName = req.getParameter("username");
        System.out.println(userName);
        String password = req.getParameter("password");
        String buttonClicked = req.getParameter("buttonSubmit");
        if(shopService.getUserDao().isLoggedIn(userName)){
            httpSession.removeAttribute(userName);
            httpSession.setAttribute("signUpError", "This email is already used, try another");
        }else{
            User user = new User("A", userName, password, UserStatus.SIGNED);
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

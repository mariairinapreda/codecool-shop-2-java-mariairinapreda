package com.codecool.shop.controller;

import com.codecool.shop.config.DatabaseManager;
import com.codecool.shop.config.HashPassword;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoImpl;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserStatus;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    TemplateEngine templateEngine;
    WebContext webContext;
    HttpSession httpSession;
    public static final long serialVersion = 1L;

    public LoginController() {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        webContext = new WebContext(req, resp, req.getServletContext());
        httpSession = req.getSession(true);
        templateEngine.process("product/login.html", webContext, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getParameter("username");
        String pass = req.getParameter("password");
        String password = HashPassword.get_SHA_512_SecurePassword(pass);
        httpSession = req.getSession(true);
        UserDao userDao = UserDaoImpl.getInstance();
        UserDao userdb = DatabaseManager.getInstance().userDao;
        if (userDao.isLoggedIn(userName) || userdb.isLoggedIn(userName)) {
            httpSession.removeAttribute(userName);
            httpSession.setAttribute("signUpError", "This email is signed in");
            resp.sendRedirect(req.getContextPath() + "/logout");
        } else {
            User user = new User("A", userName, password, UserStatus.SIGNED);
            userDao.add(user);
            userdb.updateStatus("unsigned", user.getId());
            httpSession.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

}

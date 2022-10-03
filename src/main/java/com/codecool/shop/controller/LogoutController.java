package com.codecool.shop.controller;


import com.codecool.shop.config.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    HttpSession httpSession;
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        httpSession = req.getSession(false);
        User user = (User) httpSession.getAttribute("user");
        UserDao userdb = DatabaseManager.getInstance().userDao;
        userdb.updateStatus("unsigned", user.getId());
        httpSession.invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

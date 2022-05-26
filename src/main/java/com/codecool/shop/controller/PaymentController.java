package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.utils.PaymentInfo;
import com.codecool.shop.serialization.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/payment")
public class PaymentController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private Serialization<PaymentInfo> serialization = new Serialization();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        webContext = new WebContext(req, resp, req.getServletContext());
        templateEngine.process("product/payment.html", webContext,  resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cardNumber = req.getParameter("card-number");
        String cardHolder = req.getParameter("card-holder");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        String cvv = req.getParameter("cvv");
        String buttonClicked = req.getParameter("buttonSubmit");
        if(cvv != null){
            logger.info(serialization.serialization(new PaymentInfo(cardNumber,cardHolder, month, year, cvv)));
            resp.sendRedirect(req.getContextPath()+"/");
        }
        resp.sendRedirect(req.getContextPath()+"/payment");
    }
}

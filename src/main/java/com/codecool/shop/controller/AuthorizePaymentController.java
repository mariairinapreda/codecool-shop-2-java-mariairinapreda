package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.utils.CheckoutInfo;
import com.codecool.shop.model.utils.crunchifyPayPalSDKTutorial;
import com.codecool.shop.serialization.Serialization;
import com.codecool.shop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/authorize-payment")
public class AuthorizePaymentController extends HttpServlet {
    TemplateEngine templateEngine;
    WebContext webContext;
    HttpSession httpSession;
    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private Serialization<CheckoutInfo> serialization = new Serialization();

    private void setData(HttpServletRequest request, HttpServletResponse response) {
        templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        webContext = new WebContext(request, response, request.getServletContext());
        httpSession = request.getSession();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        ShopService shopService = ShopService.getInstance();
        List<LineItem> items = shopService.getAllProdFromCart();
        int subTotal = 0;
        for (LineItem item : items) {
            int productPrice = item.getProduct().getDefaultPrice().intValue();
            subTotal += item.getQuantity() * productPrice;
        }
        int shippingInt = 10;
        int taxInt = 10;
        int totalInt = subTotal + shippingInt + taxInt;
        templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("product", "products");
        webContext.setVariable("subtotal", subTotal);
        webContext.setVariable("shipping", shippingInt);
        webContext.setVariable("tax", taxInt);
        webContext.setVariable("total", totalInt);
        templateEngine.process("product/checkout.html", webContext, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String productName = req.getParameter("product");
        String subtotal = req.getParameter("subtotal");
        String shipping = req.getParameter("shipping");
        String tax = req.getParameter("tax");
        String total = req.getParameter("total");

        if (total != null) {
            crunchifyPayPalSDKTutorial crunchifyObj = new crunchifyPayPalSDKTutorial();
            crunchifyObj.crunchifyCapturePayPalAPI(new CheckoutInfo(productName, subtotal, shipping, tax, total));
            logger.info(serialization.serialization(new CheckoutInfo(productName, subtotal, shipping, tax, total)));
            resp.sendRedirect(req.getContextPath() + "/payment");
        } else {
            resp.sendRedirect(req.getContextPath() + "/authorize-payment");
        }
    }

}

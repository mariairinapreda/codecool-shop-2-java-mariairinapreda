package com.codecool.shop.model.utils;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoImpl;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private static final String CLIENT_ID = "AV0P-VGGj0JOEqmd-V3DmyXecKulQ6emhpFptdc1lnHO70tjN081MfXr5pu2QQ_tbIUa_CtGdENQsG-1";
    private static final String CLIENT_SECRET = "EHZHdghk66DbngRMwGsyYtwOW-wvFQGiUZA0ceUUwoyc2QkK5kioZAMgtkzglxGMhq9dWaF7qfWJqYHb";
    private static final String MODE ="sandbox";


    private List<Transaction> getTransactionInformation(PaypalOrder paypalOrder){
        Details details = new Details();
        details.setShipping(paypalOrder.getShipping());
        details.setSubtotal(paypalOrder.getSubTotal());
        details.setTax(paypalOrder.getTax());

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(paypalOrder.getTotal());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(paypalOrder.getProductName());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD");
        item.setName(paypalOrder.getProductName());
        item.setPrice(paypalOrder.getSubTotal());
        item.setTax(paypalOrder.getTax());
        item.setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
                .setLastName("Peterson")
                .setEmail("william.peterson@company.com");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8888/cancel");
        redirectUrls.setReturnUrl("http://localhost:8888/review_payment");

        return redirectUrls;
    }


    public String authorizePayment(PaypalOrder paypalOrder)
            throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(paypalOrder);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }


    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }
}

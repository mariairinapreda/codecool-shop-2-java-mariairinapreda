package com.codecool.shop.model.utils;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoImpl;
import com.codecool.shop.model.Cart;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.sun.activation.registries.LogSupport.log;

public class crunchifyPayPalSDKTutorial {
    private static String crunchifyID = "AV0P-VGGj0JOEqmd-V3DmyXecKulQ6emhpFptdc1lnHO70tjN081MfXr5pu2QQ_tbIUa_CtGdENQsG-1";
    private static String crunchifySecret = "EHZHdghk66DbngRMwGsyYtwOW-wvFQGiUZA0ceUUwoyc2QkK5kioZAMgtkzglxGMhq9dWaF7qfWJqYHb";

    private static String executionMode = "sandbox"; // sandbox or production

    public void crunchifyCapturePayPalAPI(CheckoutInfo info) {


        Payer crunchifyPayer = new Payer();
        crunchifyPayer.setPaymentMethod("paypal");

        // Redirect URLs
        RedirectUrls crunchifyRedirectUrls = new RedirectUrls();
        crunchifyRedirectUrls.setCancelUrl("http://localhost:3000/crunchifyCancel");
        crunchifyRedirectUrls.setReturnUrl("http://localhost:3000/crunchifyReturn");

        // Set Payment Details Object
        Details crunchifyDetails = new Details();
        crunchifyDetails.setShipping(info.getShipping());
        crunchifyDetails.setSubtotal(info.getSubtotal());
        crunchifyDetails.setTax(info.getTax());

        // Set Payment amount
        Amount crunchifyAmount = new Amount();
        crunchifyAmount.setCurrency("USD");
        crunchifyAmount.setTotal(info.getTotal());
        crunchifyAmount.setDetails(crunchifyDetails);

        // Set Transaction information
        Transaction crunchifyTransaction = new Transaction();
        crunchifyTransaction.setAmount(crunchifyAmount);
        crunchifyTransaction.setDescription("miscellaneous objects");
        List<Transaction> crunchifyTransactions = new ArrayList<Transaction>();
        crunchifyTransactions.add(crunchifyTransaction);

        // Add Payment details
        Payment crunchifyPayment = new Payment();

        // Set Payment intent to authorize
        crunchifyPayment.setIntent("authorize");
        crunchifyPayment.setPayer(crunchifyPayer);
        crunchifyPayment.setTransactions(crunchifyTransactions);
        crunchifyPayment.setRedirectUrls(crunchifyRedirectUrls);

        // Pass the clientID, secret and mode. The easiest, and most widely used option.
        APIContext crunchifyapiContext = new APIContext(crunchifyID, crunchifySecret, executionMode);

        try {
            Payment myPayment = crunchifyPayment.create(crunchifyapiContext);

            System.out.println("createdPayment Object Details ==> " + myPayment.toString());

            // Identifier of the payment resource created
            crunchifyPayment.setId(myPayment.getId());

            PaymentExecution crunchifyPaymentExecution = new PaymentExecution();

            // Set your PayerID. The ID of the Payer, passed in the `return_url` by PayPal.
            crunchifyPaymentExecution.setPayerId("1");

            // This call will fail as user has to access Payment on UI. Programmatically
            // there is no way you can get Payer's consent.
            Payment createdAuthPayment = crunchifyPayment.execute(crunchifyapiContext, crunchifyPaymentExecution);

            // Transactional details including the amount and item details.
            Authorization crunchifyAuthorization = createdAuthPayment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization();

            log("Here is your Authorization ID" + crunchifyAuthorization.getId());

        } catch (PayPalRESTException e) {

            // The "standard" error output stream. This stream is already open and ready to
            // accept output data.
            System.err.println(e.getDetails());
        }
    }

    private void log(String string) {
        System.out.println(string);

    }

}

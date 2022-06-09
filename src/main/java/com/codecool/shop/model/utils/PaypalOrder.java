package com.codecool.shop.model.utils;

import com.codecool.shop.dao.databaseImplementation.ProductCategoryDaoDB;
import com.codecool.shop.dao.databaseImplementation.SupplierDaoDB;

import javax.sql.DataSource;

public class PaypalOrder {

    private String productName;
    private float subtotal;
    private float shipping;
    private float tax;
    private float total;
    private static PaypalOrder instance=null;

    public PaypalOrder(String productName, String subTotal, String shipping, String tax, String total) {
        this.productName = productName;
        this.subtotal = Float.parseFloat(subTotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }


    public static PaypalOrder getInstance(){
        if(instance==null){
            instance=new PaypalOrder(getInstance().productName, getInstance().getSubTotal(), getInstance().getShipping(), getInstance().getTax(), getInstance().getTotal());
        }
        return instance;

    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubTotal() {
        return String.format("%.2f", subtotal);
    }

    public void setSubTotal(float subTotal) {
        this.subtotal = subTotal;
    }

    public String getShipping() {
        return String.format("%.2f", shipping);
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public String getTax() {
        return String.format("%.2f", tax);
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return String.format("%.2f", total);
    }

    public void setTotal(float total) {
        this.total = total;
    }
}

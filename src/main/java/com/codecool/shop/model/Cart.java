package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {


    private List<LineItem> products;

    public Cart( List<LineItem> products) {
        this.products = new ArrayList<LineItem>();
    }




    public void add(LineItem product){
        products.add(product);
    }
    public List<LineItem> getCart() {
        return products;
    }

    public void delete(int productId){
        Product deletedProduct = null;
        for (LineItem product: products){
            if (product.getProduct().getId() == productId){
                deletedProduct = product.getProduct();
                break;
            }
        }
        products.remove(deletedProduct);
    }
}

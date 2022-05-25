package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    int id;
    private int userId;
    private boolean payed = false;
    private List<Product> products;

    public Cart(int id, int userId, boolean payed, List<LineItem> products) {
        this.id = id;
        this.userId = userId;
        this.payed = payed;
        this.products = new ArrayList<Product>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public void add(Product product){
        products.add(product);
    }
    public List<Product> getCart() {
        return products;
    }

    public void delete(int productId){
        Product deletedProduct = null;
        for (Product product: products){
            if (product.getId() == productId){
                deletedProduct = product;
                break;
            }
        }
        products.remove(deletedProduct);
    }
}

package com.codecool.shop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {




    private List<LineItem> products;
    private static final Logger logger = LoggerFactory.getLogger(Cart.class);


    private float total;

    public Cart( List<LineItem> products) {
        this.products = new ArrayList<>();
    }



    public void add(LineItem product){
//        if (!products.isEmpty()) {
//            for (LineItem lineItem: products) {
//                if (product.getId() == lineItem.getProduct().getId()) {
//                    lineItem.setQuantity(lineItem.getQuantity()+1);
//                    logger.info("item count increased in cart");
//                    return;
//                }
//            }
//            product.setId(products.size() + 1);
//        } else {
//            product.setId(1);
//        }
//        logger.info("item added to cart");
//        products.add(product);
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

    private void getTotalPrice(List<LineItem> items){
            for (LineItem item : items) {
                this.total +=  Float.parseFloat(item.getPrice())* item.getQuantity();
            }
        }

}


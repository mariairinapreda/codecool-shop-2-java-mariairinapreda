package com.codecool.shop.model;

public class LineItem extends BaseModel{
    private int quantity;
    private String price;
    private Product product;


    public LineItem(int quantity, String price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

//    public String getTotalPrice(String price, int quantity){
//        int total = Integer.parseInt(price)* quantity;
//        return String.valueOf(total);
//    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productQuantity: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.price,
                this.quantity,
                this.product.getId());
    }

}

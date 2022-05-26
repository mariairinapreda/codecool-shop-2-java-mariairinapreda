package com.codecool.shop.model.utils;

public class PaymentInfo {
    String cardNumber;
    String cardHolder;
    String month;
    String year;
    String cvv;

    public PaymentInfo(String cardNumber, String cardHolder, String month, String year, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
    }

    public PaymentInfo() {
    }


}

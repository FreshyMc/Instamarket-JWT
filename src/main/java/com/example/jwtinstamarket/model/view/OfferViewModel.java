package com.example.jwtinstamarket.model.view;

import java.math.BigDecimal;

public class OfferViewModel {
    private String title;
    private String description;
    private BigDecimal price;
    private String sellerFullname;
    private String sellerEmail;

    public OfferViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public OfferViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSellerFullname() {
        return sellerFullname;
    }

    public OfferViewModel setSellerFullname(String sellerFullname) {
        this.sellerFullname = sellerFullname;
        return this;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public OfferViewModel setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
        return this;
    }
}

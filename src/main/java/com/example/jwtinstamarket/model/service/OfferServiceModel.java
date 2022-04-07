package com.example.jwtinstamarket.model.service;

import java.math.BigDecimal;

public class OfferServiceModel {
    private String title;
    private String description;
    private BigDecimal price;

    public OfferServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public OfferServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}

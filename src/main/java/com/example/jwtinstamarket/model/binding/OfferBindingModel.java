package com.example.jwtinstamarket.model.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class OfferBindingModel {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @Min(1)
    private BigDecimal price;

    public OfferBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public OfferBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}

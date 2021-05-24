package com.endgame.productservice.dto;

import java.util.Date;
import java.util.List;

public class ProductDto {

    private long id;
    private String name;
    private double price;
    private double tax;
    private double discount;
    private Date create_date;
    private Boolean active;
    private long category_id;
    private long quantity;
    private String description;
    private List<ImageDto> Images;

    public List<ImageDto> getImages() {
        return Images;
    }

    public void setImages(List<ImageDto> images) {
        Images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

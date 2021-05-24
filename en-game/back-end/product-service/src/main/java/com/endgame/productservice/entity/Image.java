package com.endgame.productservice.entity;

import javax.persistence.*;

@Entity(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private byte[] Images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImages() {
        return Images;
    }

    public void setImages(byte[] images) {
        Images = images;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }
}

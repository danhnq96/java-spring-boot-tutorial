package com.endgame.adminservice.orm;

import com.endgame.adminservice.models.Category;
import com.endgame.adminservice.models.Image;
import com.endgame.adminservice.models.Product;
import com.endgame.adminservice.models.Color;
import lombok.Data;

@Data
public class ProductORM {
    private Product product;
    private Category category;
    private Image image;
    private Color color;

    public ProductORM(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    public ProductORM(Color color) {
        this.color = color;
    }
    
    public ProductORM(Product product, Category category, Image image) {
        this.product = product;
        this.category = category;
        this.image = image;
    }
}

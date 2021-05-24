package com.endgame.adminservice.dto.product;

import com.endgame.adminservice.models.Color;
import com.endgame.adminservice.dto.image.ImageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal newPrice = BigDecimal.ZERO;
    private BigDecimal oldPrice = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private Long categoryId;
    private BigDecimal weight = BigDecimal.ZERO;
    private String description;
    private BigDecimal ratingCount = BigDecimal.ZERO;
    private BigDecimal ratingValue = BigDecimal.ZERO;
    private Long availibilityCount = 0L;
    private Long cartCount = 0L;
    private boolean active;
    private List<ImageDTO> images;
    private List<ImageDTO> imagesDeleted;
    private List<ImageDTO> imagesAdd;
    private ImageDTO imageMain;
    private List<Color> colors;
    private String size;
}

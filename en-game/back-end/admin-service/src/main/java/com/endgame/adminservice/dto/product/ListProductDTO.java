package com.endgame.adminservice.dto.product;

import com.endgame.adminservice.dto.category.CategoryListProductDTO;
import com.endgame.adminservice.dto.image.ImageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@Data
public class ListProductDTO {
    private Long id;
    private String name;
    private CategoryListProductDTO category;
    private BigDecimal newPrice = BigDecimal.ZERO;
    private BigDecimal oldPrice = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private Long cartCount = 0L;
    private BigDecimal weight = BigDecimal.ZERO;
    private Long availibilityCount = 0L;
    private String createdDate;
    private String updatedDate = createdDate;
    private ImageDTO image;
    private boolean active;

}

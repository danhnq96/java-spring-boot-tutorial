package com.endgame.adminservice.dto.product;

import com.endgame.adminservice.dto.category.CategoryListProductDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductExportExcelDTO {
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
    private String size;
    private String updatedDate = createdDate;
    private boolean active;
}

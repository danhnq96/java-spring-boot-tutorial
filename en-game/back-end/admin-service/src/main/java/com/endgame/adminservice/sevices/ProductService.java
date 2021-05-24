package com.endgame.adminservice.sevices;

import com.endgame.adminservice.dto.category.ListCategoryDTO;
import com.endgame.adminservice.dto.image.UploadImageMainDTO;
import com.endgame.adminservice.dto.product.ListProductDTO;
import com.endgame.adminservice.dto.product.ProductDTO;
import com.endgame.adminservice.dto.product.ProductExportExcelDTO;
import com.endgame.adminservice.models.Color;
import com.endgame.adminservice.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Page<ListProductDTO> getListProducts(Pageable pageable, String search);

    Page<ProductExportExcelDTO> getListProductsExportToExcel(String search);

    ProductDTO findById(Long id);

    void saveProduct(ProductDTO productDTO);

    ListCategoryDTO getCategoryName(Long id);

    Page<ListCategoryDTO> getListCategories();

    Image uploadImageMain(UploadImageMainDTO uploadImageMainDTO);

    void deleteColor(Color color);
}

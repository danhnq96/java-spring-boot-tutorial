package com.endgame.adminservice.sevices;

import com.endgame.adminservice.dto.category.CategoryDTO;
import com.endgame.adminservice.dto.category.CategoryExportExcelDTO;
import com.endgame.adminservice.dto.category.ListCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface CategoryService {
    Page<ListCategoryDTO> getListCategories(Pageable pageable, String search);
    Page<CategoryExportExcelDTO> getListCategoriesExportToExcel(String search);
    CategoryDTO findById(Long id);
    void saveCategory(CategoryDTO categoryDTO);
}

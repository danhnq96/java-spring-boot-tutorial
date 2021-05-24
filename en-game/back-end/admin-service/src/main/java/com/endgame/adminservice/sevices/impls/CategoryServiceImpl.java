package com.endgame.adminservice.sevices.impls;

import com.endgame.adminservice.commons.FormatString;
import com.endgame.adminservice.models.Category;
import com.endgame.adminservice.sevices.CategoryService;
import com.endgame.adminservice.dto.category.CategoryDTO;
import com.endgame.adminservice.dto.category.CategoryExportExcelDTO;
import com.endgame.adminservice.dto.category.ListCategoryDTO;
import com.endgame.adminservice.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<ListCategoryDTO> getListCategories(Pageable pageable, String search) {
        ModelMapper modelMapper = new ModelMapper();
        Page<Category> categories = categoryRepository.getListCategories(search, pageable);
        return categories.map(category -> modelMapper.map(category, ListCategoryDTO.class));
    }

    @Override
    public Page<CategoryExportExcelDTO> getListCategoriesExportToExcel(String search) {
        ModelMapper modelMapper = new ModelMapper();
        Page<Category> categories = categoryRepository.getListCategories(search, PageRequest.of(0, Integer.MAX_VALUE));
        return categories.map(category -> modelMapper.map(category, CategoryExportExcelDTO.class));
    }

    @Override
    public CategoryDTO findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Category category = categoryRepository.findById(id).orElse(null);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        Category category;
        if (categoryDTO.getId() == null) {
            category = new Category();
        } else {
            category = categoryRepository.findById(categoryDTO.getId()).orElse(null);
        }
        if(category != null) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(categoryDTO, category);
            category.setName(FormatString.formatName(category.getName()));
            categoryRepository.save(category);
        }
    }
}

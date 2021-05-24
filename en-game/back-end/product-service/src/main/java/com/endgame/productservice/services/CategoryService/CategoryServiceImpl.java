package com.endgame.productservice.services.CategoryService;

import com.endgame.productservice.dto.CategoryDto;
import com.endgame.productservice.entity.Category;
import com.endgame.productservice.payload.request.CategoryRequest;
import com.endgame.productservice.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public Optional<Category> add(CategoryRequest categoryRequest) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Category categoryReq =  modelMapper.map(categoryRequest, Category.class);
        return Optional.of(categoryRepo.save(categoryReq));
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> rootCateList = categoryRepo.findAll();
        List<CategoryDto> dtoCateList = rootCateList.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setHasSubCategory(category.getHasSubCategory());
            categoryDto.setParentId(category.getParent().getId());
            categoryDto.setActive(category.getActive());
            return categoryDto;
        }).collect(Collectors.toList());
        return dtoCateList;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

}

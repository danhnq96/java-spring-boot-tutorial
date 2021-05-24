package com.endgame.productservice.services.CategoryService;

import com.endgame.productservice.dto.CategoryDto;
import com.endgame.productservice.entity.Category;
import com.endgame.productservice.payload.request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    Optional<Category> add(CategoryRequest categoryRequest) throws Exception;

    List<CategoryDto> getAll();

    Optional<Category> findById(Long id) ;

}

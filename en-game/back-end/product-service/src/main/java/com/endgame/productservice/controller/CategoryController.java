package com.endgame.productservice.controller;

import com.endgame.productservice.dto.CategoryDto;
import com.endgame.productservice.entity.Category;
import com.endgame.productservice.exception.ResourceNotFoundExceptionHandle;
import com.endgame.productservice.payload.request.CategoryRequest;
import com.endgame.productservice.services.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("getAll")
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAll();
    }

    @PostMapping(value = "add")
    public Category addCategory(@Valid @RequestBody CategoryRequest categoryRequest) throws Exception {
        return categoryService.add(categoryRequest).orElseThrow(RuntimeException::new);
    }

    @PutMapping(value = "update")
    public Category updateCategory(@Valid @RequestBody CategoryRequest categoryRequest) throws Exception {
        return categoryService.add(categoryRequest).orElseThrow(RuntimeException::new);
    }

    @GetMapping("id")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundExceptionHandle {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandle("Category not found for this id :: " + id));
        return ResponseEntity.ok().body(category);
    }

}

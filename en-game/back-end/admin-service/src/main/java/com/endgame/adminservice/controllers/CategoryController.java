package com.endgame.adminservice.controllers;

import com.endgame.adminservice.commons.FuncHelper;
import com.endgame.adminservice.commons.SortData;
import com.endgame.adminservice.dto.category.CategoryDTO;
import com.endgame.adminservice.dto.category.CategoryExportExcelDTO;
import com.endgame.adminservice.dto.category.ListCategoryDTO;
import com.endgame.adminservice.models.ErrorAPI;
import com.endgame.adminservice.sevices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4201", allowedHeaders = "*")
@RequestMapping("/api/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    FuncHelper funcHelper;


    //    API Get List Categories
    @GetMapping(value = "")
    public ResponseEntity<Page<ListCategoryDTO>> getListCategories(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                   @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                                   @RequestParam(name = "search", required = false, defaultValue = "") String search,
                                                                   @RequestParam(defaultValue = "id,asc") String[] sort) {
        Page<ListCategoryDTO> lists = categoryService.getListCategories(PageRequest.of(page, size, Sort.by(SortData.getOrderByParamSort(sort))), search);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    //    API Get List To Export Excel
    @GetMapping(value = "/export")
    public ResponseEntity<List<CategoryExportExcelDTO>> getListCategoriesExportToExcel(
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getListCategoriesExportToExcel(search).getContent());
    }

    //    API Get Category By Id
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }

    //    API Save Category
    @PostMapping(value = "/save")
    public ResponseEntity<ErrorAPI> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        funcHelper = new FuncHelper();
        try {
            categoryService.saveCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorAPI("Success", categoryDTO.getName(), 1));
        } catch (Exception err) {
            ErrorAPI errorAPI = funcHelper.getErrorSqlApi(err);
            return ResponseEntity.status(900).body(errorAPI);
        }
    }
}
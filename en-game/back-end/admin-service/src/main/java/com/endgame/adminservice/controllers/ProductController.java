package com.endgame.adminservice.controllers;

import com.endgame.adminservice.dto.product.ListProductDTO;
import com.endgame.adminservice.dto.product.ProductDTO;
import com.endgame.adminservice.dto.product.ProductExportExcelDTO;
import com.endgame.adminservice.sevices.ProductService;
import com.endgame.adminservice.commons.ConstURL;
import com.endgame.adminservice.commons.FuncHelper;
import com.endgame.adminservice.commons.SortData;
import com.endgame.adminservice.dto.category.ListCategoryDTO;
import com.endgame.adminservice.dto.image.UploadImageMainDTO;
import com.endgame.adminservice.models.Color;
import com.endgame.adminservice.models.ErrorAPI;
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
@RequestMapping(ConstURL.URL_PRODUCTS)
public class ProductController {

    @Autowired
    ProductService productService;

    FuncHelper funcHelper;

    //    API Get List Product
    @GetMapping(value = "")
    public ResponseEntity<Page<ListProductDTO>> getListProducts(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                                @RequestParam(name = "search", required = false, defaultValue = "") String search,
                                                                @RequestParam(defaultValue = "id,asc") String[] sort) {
        Page<ListProductDTO> lists = productService.getListProducts(PageRequest.of(page, size, Sort.by(SortData.getOrderByParamSort(sort))), search);
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }

    //    API Get List To Export Excel
    @GetMapping(value = ConstURL.URL_EXPORT_EXCEL)
    public ResponseEntity<List<ProductExportExcelDTO>> getListProductsExportToExcel(
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getListProductsExportToExcel(search).getContent());
    }

    //    API Get Product By Id
    @GetMapping(value = ConstURL.URL_DETAIL)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    //    API Get List Category
    @GetMapping(value = ConstURL.URL_LIST_CATEGORIES)
    public ResponseEntity<List<ListCategoryDTO>> getListCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getListCategories().getContent());
    }

//    //    API Get Name Category
//    @GetMapping(value = "/category/{id}")
//    public ResponseEntity<ListCategoryDTO> getListCategories(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(productService.getCategoryName(id));
//    }

    //    API Save Product
    @PostMapping(value = ConstURL.URL_SAVE)
    public ResponseEntity<ErrorAPI> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        funcHelper = new FuncHelper();
        try {
            productService.saveProduct(productDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorAPI("Success", productDTO.getName(), 1));
        } catch (Exception err) {
            ErrorAPI errorAPI = funcHelper.getErrorSqlApi(err);
            return ResponseEntity.status(900).body(errorAPI);
        }
    }

    //    API Upload Image Main Product
    @PostMapping(value = ConstURL.URL_UPLOAD_IMAGE_MAIN)
    public ResponseEntity<?> uploadImageMain(@Valid @RequestBody UploadImageMainDTO uploadImageMainDTO) {
        funcHelper = new FuncHelper();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.uploadImageMain(uploadImageMainDTO));
        } catch (Exception err) {
            ErrorAPI errorAPI = funcHelper.getErrorSqlApi(err);
            return ResponseEntity.status(900).body(errorAPI);
        }
    }

    //    API Delete Color
    @PostMapping(value = ConstURL.URL_DELETE_COLOR)
    public ResponseEntity<?> deleteColor(@RequestBody Color color) {
        funcHelper = new FuncHelper();
        try {
            productService.deleteColor(color);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception err) {
            ErrorAPI errorAPI = funcHelper.getErrorSqlApi(err);
            return ResponseEntity.status(900).body(errorAPI);
        }
    }
}

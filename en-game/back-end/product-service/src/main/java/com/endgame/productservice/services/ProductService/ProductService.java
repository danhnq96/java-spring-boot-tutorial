package com.endgame.productservice.services.ProductService;

import com.endgame.productservice.dto.ProductDto;
import com.endgame.productservice.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {

    Product createProduct(Product product);

    Optional<ProductDto> getProductDtoById(long productId) throws Exception;

}

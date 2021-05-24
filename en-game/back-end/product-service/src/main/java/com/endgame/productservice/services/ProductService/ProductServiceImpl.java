package com.endgame.productservice.services.ProductService;

import com.endgame.productservice.dto.ImageDto;
import com.endgame.productservice.dto.ProductDto;
import com.endgame.productservice.entity.Image;
import com.endgame.productservice.entity.Product;
import com.endgame.productservice.repository.ImageRepository;
import com.endgame.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ImageRepository imageRepo;

    @Override
    public Product createProduct(Product product) {
        Product rootProduct = new Product();
        rootProduct.setName(product.getName());
        rootProduct.setTax(product.getTax());
        rootProduct.setPrice(product.getPrice());
        rootProduct.setDiscount(product.getDiscount());
        rootProduct.setQuantity(product.getQuantity());
        rootProduct.setDescription(product.getDescription());
        rootProduct.setCreate_date(product.getCreate_date());
        rootProduct.setActive(product.getActive());
        rootProduct.setCategory_id(product.getCategory_id());
        productRepo.save(rootProduct);
        return rootProduct;
    }

    //Product detail
    @Override
    public Optional<ProductDto> getProductDtoById(long productId) throws Exception {
        Optional<Product> productById = Optional.ofNullable(productRepo.findById(productId).orElseThrow(() -> new Exception("Product is not found")));
        Optional<ProductDto> productDto = productById.map(product -> {
            ProductDto productDtoMapper = new ProductDto();
            productDtoMapper.setId(product.getId());
            productDtoMapper.setName(product.getName());
            productDtoMapper.setPrice(product.getPrice());
            productDtoMapper.setDiscount(product.getDiscount());
            productDtoMapper.setCreate_date(product.getCreate_date());
            productDtoMapper.setTax(product.getTax());
            productDtoMapper.setQuantity(product.getQuantity());
            productDtoMapper.setDescription(product.getDescription());
            productDtoMapper.setCategory_id(product.getCategory_id());
            List<Image> imageList =imageRepo.findAllImageById(product.getId());
            List<ImageDto> imageListDto = new ArrayList<>();
            for (Image image: imageList) {
                ImageDto imageDto = new ImageDto();
                imageDto.setImages(decompressBytes(image.getImages()));
                imageListDto.add(imageDto);
                productDtoMapper.setImages(imageListDto);
            }
            return productDtoMapper;
        });
        return productDto;
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        return outputStream.toByteArray();
    }


}

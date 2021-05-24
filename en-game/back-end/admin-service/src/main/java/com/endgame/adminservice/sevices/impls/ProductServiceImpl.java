package com.endgame.adminservice.sevices.impls;

import com.endgame.adminservice.commons.FormatString;
import com.endgame.adminservice.commons.FuncHelper;
import com.endgame.adminservice.dto.product.ListProductDTO;
import com.endgame.adminservice.dto.product.ProductDTO;
import com.endgame.adminservice.dto.product.ProductExportExcelDTO;
import com.endgame.adminservice.models.Category;
import com.endgame.adminservice.models.Image;
import com.endgame.adminservice.models.Product;
import com.endgame.adminservice.orm.ProductORM;
import com.endgame.adminservice.sevices.ProductService;
import com.endgame.adminservice.dto.category.CategoryListProductDTO;
import com.endgame.adminservice.dto.category.ListCategoryDTO;
import com.endgame.adminservice.dto.image.ImageDTO;
import com.endgame.adminservice.dto.image.UploadImageMainDTO;
import com.endgame.adminservice.models.Color;
import com.endgame.adminservice.repositories.CategoryRepository;
import com.endgame.adminservice.repositories.ColorRepository;
import com.endgame.adminservice.repositories.ImageRepository;
import com.endgame.adminservice.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ColorRepository colorRepository;

    FuncHelper funcHelper;

    @Override
    public Page<ListProductDTO> getListProducts(Pageable pageable, String search) {
        ModelMapper modelMapper = new ModelMapper();
        Page<ProductORM> products = productRepository.getListProducts(search, pageable);
        return products.map(prd -> {
            ListProductDTO productDTO = modelMapper.map(prd.getProduct(), ListProductDTO.class);
            productDTO.setCategory(modelMapper.map(prd.getCategory(), CategoryListProductDTO.class));
            if(prd.getImage() != null) {
                productDTO.setImage(modelMapper.map(prd.getImage(), ImageDTO.class));
            } else{
                productDTO.setImage(new ImageDTO());
            }
            return productDTO;
        });
    }

    @Override
    public Page<ProductExportExcelDTO> getListProductsExportToExcel(String search) {
        ModelMapper modelMapper = new ModelMapper();
        Page<ProductORM> products = productRepository.getListProducts(search, PageRequest.of(0, Integer.MAX_VALUE));
        return products.map(prd -> {
            ProductExportExcelDTO productDTO = modelMapper.map(prd.getProduct(), ProductExportExcelDTO.class);
            productDTO.setCategory(modelMapper.map(prd.getCategory(), CategoryListProductDTO.class));
            return productDTO;
        });
    }

    @Override
    public ProductDTO findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductORM> products = productRepository.findProductById(id);
        ProductDTO productDTO = new ProductDTO();
        if (products.size() > 0) {
            productDTO = modelMapper.map(products.get(0).getProduct(), ProductDTO.class);
            productDTO.setCategoryId(products.get(0).getCategory().getId());
            List<ImageDTO> images = new ArrayList<>();
            List<Color> colors = new ArrayList<>();
            for (ProductORM productORM : products) {
                if (productORM.getImage() != null) {
                    images.add(modelMapper.map(productORM.getImage(), ImageDTO.class));
                }
            }
            for(ProductORM productORM: productRepository.findColorByProductId(id)){
                if(productORM.getColor() != null){
                    colors.add(productORM.getColor());
                }
            }
            productDTO.setImages(images);
            productDTO.setColors(colors);
        }
        Image imageMain = imageRepository.findByProductIdAndMainImage(productDTO.getId(), true);
        if(imageMain != null) {
            productDTO.setImageMain(modelMapper.map(imageMain, ImageDTO.class));
        }
        return productDTO;
    }

    @Override
    public void saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        funcHelper = new FuncHelper();
        product.setCreatedDate(funcHelper.convertDateToString("yyyy-MM-dd hh:mm:ss.sss", Calendar.getInstance().getTime()));

        if (productDTO.getId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
            }
            product.setUpdatedDate(funcHelper.convertDateToString("yyyy-MM-dd hh:mm:ss.sss", Calendar.getInstance().getTime()));
        }
        if(product.getNewPrice() != null && !productDTO.getNewPrice().equals(BigDecimal.ZERO) && !product.getNewPrice().equals(productDTO.getNewPrice())){
            productDTO.setOldPrice(product.getNewPrice());
        }
        product.setName(FormatString.formatName(product.getName()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(productDTO, product);
        Product saveProduct = productRepository.save(product);

        if(productDTO.getColors() != null){
            for(Color color : productDTO.getColors()){
                if(color.getName() != null && !color.getName().equals("")){
                    color.setProductId(saveProduct.getId());
                    colorRepository.save(color);
                }
            }
        }

        if (productDTO.getImagesAdd() != null && productDTO.getImagesAdd().size() > 0) {
            for (ImageDTO imageDTO : productDTO.getImagesAdd()) {
                Image imageProduct = new Image();

                modelMapper = new ModelMapper();
                modelMapper.getConfiguration()
                        .setMatchingStrategy(MatchingStrategies.STRICT);
                modelMapper.map(imageDTO, imageProduct);
                imageProduct.setProductId(saveProduct.getId());
                imageRepository.save(imageProduct);
            }
        }

        if (productDTO.getImagesDeleted() != null && productDTO.getImagesDeleted().size() > 0) {
            for (ImageDTO imageDTO : productDTO.getImagesDeleted()) {
                Image image = new Image();
                modelMapper = new ModelMapper();
                modelMapper.getConfiguration()
                        .setMatchingStrategy(MatchingStrategies.STRICT);
                modelMapper.map(imageDTO, image);
                image.setProductId(saveProduct.getId());
                imageRepository.delete(image);
            }
        }
    }

    @Override
    public Page<ListCategoryDTO> getListCategories() {
        ModelMapper modelMapper = new ModelMapper();
        Page<Category> categories = categoryRepository.findAllByActiveTrue(PageRequest.of(0, Integer.MAX_VALUE));
        return categories.map(category -> modelMapper.map(category, ListCategoryDTO.class));
    }

    @Override
    public Image uploadImageMain(UploadImageMainDTO uploadImageMainDTO) {
        if (uploadImageMainDTO.getImageMainOld() != null) {
            Image image = new Image();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(uploadImageMainDTO.getImageMainOld(), image);
            imageRepository.delete(image);
        }
        if (uploadImageMainDTO.getImageMainNew() != null) {
            Image image = new Image();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(uploadImageMainDTO.getImageMainNew(), image);
            return imageRepository.save(image);
        }
        return null;
    }

    @Override
    public void deleteColor(Color color) {
        if(color != null){
            colorRepository.delete(color);
        }
    }

    @Override
    public ListCategoryDTO getCategoryName(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            ListCategoryDTO listCategoryDTO = new ListCategoryDTO();
            listCategoryDTO.setName(category.getName());
            return listCategoryDTO;
        }
        return null;
    }
}

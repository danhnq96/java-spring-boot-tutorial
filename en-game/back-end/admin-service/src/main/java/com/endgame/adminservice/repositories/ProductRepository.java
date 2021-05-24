package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.Product;
import com.endgame.adminservice.orm.ProductORM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.endgame.adminservice.orm.ProductORM(product, ca, img) " +
            "FROM Product product " +
            "INNER JOIN Category ca " +
            "ON ca.id = product.categoryId " +
            "LEFT JOIN Image img " +
            "ON img.productId = product.id AND img.mainImage = 1 " +
            "WHERE product.name LIKE %:search% " +
            "OR ca.name LIKE %:search% " +
            "OR product.newPrice LIKE %:search% " +
            "OR product.oldPrice LIKE %:search% " +
            "OR product.createdDate LIKE %:search% " +
            "OR product.updatedDate LIKE %:search% " +
            "OR product.weight LIKE %:search% " +
            "OR product.discount LIKE %:search% " +
            "OR product.cartCount LIKE %:search%"
    )
    Page<ProductORM> getListProducts(String search, Pageable pageable);

    @Query("SELECT new com.endgame.adminservice.orm.ProductORM(product, ca, img) " +
            "FROM Product product " +
            "INNER JOIN Category ca " +
            "ON ca.id = product.categoryId " +
            "LEFT JOIN Image img " +
            "ON img.productId = product.id AND img.mainImage = 0 " +
            "WHERE product.id = :id "
    )
    List<ProductORM> findProductById(@Param("id") Long id);

    @Query("SELECT new com.endgame.adminservice.orm.ProductORM(co) " +
            "FROM Product product " +
            "INNER JOIN Color co " +
            "ON co.productId = product.id " +
            "WHERE product.id = :id "
    )
    List<ProductORM> findColorByProductId(@Param("id") Long id);
}

package com.endgame.productservice.repository;

import com.endgame.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    @Query("Select pro FROM Product pro WHERE pro.name like %?1%")
    Page<Product> findAllByName(String name, Pageable pageable);

    @Query("Select pro FROM Product pro WHERE pro.category_id=:cat_id")
    Page<Product> getByCategoryId(@Param("cat_id")Long cat_id, Pageable pageable);

    List<Product> findTop4ByOrderByIdAsc();

    @Query("Select pro FROM Product pro WHERE pro.category_id=:cat_id and pro.id <> :id")
    Page<Product> getByCategoryRelatedId(@Param("cat_id")Long cat_id, @Param("id")Long id,Pageable pageable);


}

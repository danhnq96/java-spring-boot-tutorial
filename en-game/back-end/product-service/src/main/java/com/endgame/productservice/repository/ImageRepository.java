package com.endgame.productservice.repository;

import com.endgame.productservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("Select images FROM Images images WHERE images.products.id = :product_id")
    List<Image> findAllImageById(Long product_id);

    List<Image> findFirstByProducts_IdOrderById(Long product_id);

    Image findFirstByProducts_IdOrderByIdAsc(Long product_id);

}

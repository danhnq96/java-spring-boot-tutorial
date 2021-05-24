package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByProductIdAndMainImage(Long productId, boolean mainImage);
}

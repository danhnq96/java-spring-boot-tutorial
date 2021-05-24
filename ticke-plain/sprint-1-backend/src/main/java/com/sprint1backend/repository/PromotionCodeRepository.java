package com.sprint1backend.repository;

import com.sprint1backend.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionCodeRepository extends JpaRepository<Promotion,Long> {
}

package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT ca FROM Category ca  WHERE ca.name LIKE %:search%")
    Page<Category> getListCategories(String search, Pageable pageable);

    Optional<Category> findById(Long id);

    Page<Category> findAllByActiveTrue(Pageable pageable);
}

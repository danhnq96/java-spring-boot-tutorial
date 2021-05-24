package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}

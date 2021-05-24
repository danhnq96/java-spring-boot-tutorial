package com.csf.whoami.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.TbMenu;

@Repository
public interface MenuRepository extends JpaRepository<TbMenu, String> {
}

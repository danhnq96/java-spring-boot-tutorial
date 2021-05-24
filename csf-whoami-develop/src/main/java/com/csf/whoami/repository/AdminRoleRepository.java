package com.csf.whoami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.TbAdminRole;

@Repository
public interface AdminRoleRepository extends JpaRepository<TbAdminRole, Long>, JpaSpecificationExecutor<TbAdminRole> {
    TbAdminRole findByAdminId(Long tbAdminId);
}

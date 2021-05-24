/**
 *
 */
package com.csf.whoami.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.backend.entity.RoleEntity;

/**
 * @author tuan
 *
 */
public interface RolesRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findByName(String roleName);
}

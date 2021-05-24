/**
 *
 */
package com.csf.whoami.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.backend.entity.UserRoleEntity;

/**
 * @author tuan
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {

}

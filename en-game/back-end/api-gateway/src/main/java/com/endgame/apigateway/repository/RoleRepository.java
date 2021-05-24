package com.endgame.apigateway.repository;

import com.endgame.apigateway.entity.ERole;
import com.endgame.apigateway.entity.Role;
import com.endgame.apigateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/08
 * @project: auth-service
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);

  Optional<Role> findById(Long id);
}

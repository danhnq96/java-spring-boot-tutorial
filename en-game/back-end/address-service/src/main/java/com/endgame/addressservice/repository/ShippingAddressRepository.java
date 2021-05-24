package com.endgame.addressservice.repository;

import com.endgame.addressservice.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/16
 * @project: address-service
 */
@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
  Optional<ShippingAddress> findByUserId(Long userId);
}

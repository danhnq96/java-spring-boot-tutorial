package com.endgame.orderservice.repository;

import com.endgame.orderservice.entity.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/14
 * @project: order-service
 */
@Repository
public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {
  Collection<Cart> findAllByUserId(Long userId);
  Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);
  void deleteByUserId(Long userId);

}

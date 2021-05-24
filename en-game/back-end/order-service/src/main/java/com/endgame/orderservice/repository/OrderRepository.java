package com.endgame.orderservice.repository;

import com.endgame.orderservice.entity.Order;
import com.endgame.orderservice.payload.response.CountOrderStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/01
 * @project: order-service
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
  Optional<Order> findByUserId(Long userId);

  Page<Order> findAllByUserId(Long userId, Pageable pageable);

  @Query(nativeQuery = true, value = "SELECT o.status, COUNT(*) as 'count'" +
    " FROM " +
    "Orders o " +
    "WHERE o.user_id = :userId GROUP BY o.status ORDER BY o.status")
  Collection<CountOrderStatusResponse> getCountOrderStatus(@Param("userId") Long userId);
}

package com.endgame.orderservice.repository;

import com.endgame.orderservice.entity.Order;
import com.endgame.orderservice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/01
 * @project: order-service
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}

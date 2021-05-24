package com.endgame.orderservice.service;

import com.endgame.orderservice.entity.Order;
import com.endgame.orderservice.entity.OrderStatus;
import com.endgame.orderservice.payload.request.GetOrdersRequest;
import com.endgame.orderservice.payload.response.CountOrderStatusResponse;
import com.endgame.orderservice.payload.response.GetOrdersResponse;
import com.endgame.orderservice.repository.OrderRepository;
import com.stripe.exception.StripeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/04
 * @project: order-service
 */
@Service
@Transactional
public class OrderService {
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  CheckoutService checkoutService;

  ModelMapper modelMapper = new ModelMapper();

  public GetOrdersResponse getOrdersPagination(GetOrdersRequest getOrdersRequest) {
    Page<Order> orders =
      orderRepository.findAllByUserId(getOrdersRequest.getUserId(), PageRequest.of(getOrdersRequest.getPageNumber(),
        getOrdersRequest.getPageSize()));

    return new GetOrdersResponse(orders);
  }

  public Collection<CountOrderStatusResponse> getCountOrderStatus(Long userId) {
    return orderRepository.getCountOrderStatus(userId);
  }

  public boolean cancelOrderById(Long orderId) throws StripeException {
    Optional<Order> result = orderRepository.findById(orderId);

    if (result.isPresent()) {
      Order order = result.get();

      if (order.getStatus() == OrderStatus.PROCESSING) {
        checkoutService.cancel(order.getPaymentId());
        order.setStatus(OrderStatus.CANCELED);
      } else {
        checkoutService.refund(order.getPaymentId());
        order.setStatus(OrderStatus.REFUND);
      }

      orderRepository.save(order);
      return true;
    }

    return false;
  }
}

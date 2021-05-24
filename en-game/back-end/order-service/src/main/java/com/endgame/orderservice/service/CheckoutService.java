package com.endgame.orderservice.service;

import com.endgame.orderservice.config.StripeProperties;
import com.endgame.orderservice.entity.Order;
import com.endgame.orderservice.entity.OrderDetail;
import com.endgame.orderservice.entity.OrderStatus;
import com.endgame.orderservice.payload.request.CreatePaymentIntentRequest;
import com.endgame.orderservice.payload.response.CreatePaymentIntentResponse;
import com.endgame.orderservice.repository.OrderDetailRepository;
import com.endgame.orderservice.repository.OrderRepository;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/20
 * @project: order-service
 */
@Service
@Transactional
public class CheckoutService {
  private final StripeProperties stripeProperties;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  OrderDetailRepository orderDetailRepository;

  public CheckoutService(StripeProperties stripeProperties) {
    this.stripeProperties = stripeProperties;
  }

  public CreatePaymentIntentResponse createPaymentIntent(Order createPaymentIntentRequest) throws StripeException {
    // Create new order
    Order order = new Order();
    order.setAmount(createPaymentIntentRequest.getAmount());
    order.setOrderCode(createPaymentIntentRequest.getOrderCode());
    order.setDiscount(createPaymentIntentRequest.getDiscount());
    order.setEmployeeId(createPaymentIntentRequest.getEmployeeId());
    order.setUserId(createPaymentIntentRequest.getUserId());
    order.setShippingFee(createPaymentIntentRequest.getShippingFee());
    order.setShippingId((createPaymentIntentRequest.getShippingId()));
    order.setStatus(createPaymentIntentRequest.getStatus());
    order.setTax(createPaymentIntentRequest.getTax());

    Order newOrder = orderRepository.save(order);

    Collection<OrderDetail> details = new ArrayList<>();

    createPaymentIntentRequest.getOrderDetails().forEach(detail -> {
      detail.setOrder(newOrder);
      details.add(detail);
    });

    orderDetailRepository.saveAll(details);

    // Create payment intent
    Stripe.apiKey = stripeProperties.getSecretKey();

    List<Object> paymentMethodTypes =
      new ArrayList<>();
    paymentMethodTypes.add("card");
    Map<String, Object> params = new HashMap<>();
    params.put("amount", createPaymentIntentRequest.getAmount().setScale(0, RoundingMode.UP).intValue() * 100);
    params.put("currency", "usd");
    params.put(
      "payment_method_types",
      paymentMethodTypes
    );

    Map<String, String> initialMetadata = new HashMap<>();
    initialMetadata.put("orderId", newOrder.getId().toString());
    params.put("metadata", initialMetadata);

    PaymentIntent paymentIntent = PaymentIntent.create(params);

    newOrder.setPaymentId(paymentIntent.getId());

    orderRepository.save(newOrder);

    return new CreatePaymentIntentResponse(paymentIntent.getClientSecret(), newOrder.getId());
  }

  public boolean updateOrderStatus(Long orderId, OrderStatus status) {
    Optional<Order> result = orderRepository.findById(orderId);

    if (result.isPresent()) {
      Order order = result.get();
      order.setStatus(status);

      orderRepository.save(order);
      return true;
    }

    return false;
  }

  public Refund refund(String paymentIntentId) throws StripeException {
    return Refund.create(RefundCreateParams.builder().setPaymentIntent(paymentIntentId).build());
  }

  public boolean cancel(String paymentIntentId) throws StripeException {
    PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

    if (!paymentIntent.getStatus().equals("canceled") && !paymentIntent.getStatus().equals("succeeded")) {
      paymentIntent.cancel();
      return true;
    }

    return false;
  }
}

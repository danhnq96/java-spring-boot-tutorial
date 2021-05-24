package com.endgame.orderservice.controller;

import com.endgame.orderservice.entity.Cart;
import com.endgame.orderservice.payload.request.AddCartRequest;
import com.endgame.orderservice.payload.request.GetCartsRequest;
import com.endgame.orderservice.payload.request.UpdateCartRequest;
import com.endgame.orderservice.payload.response.GetCartResponse;
import com.endgame.orderservice.payload.response.SuccessResponse;
import com.endgame.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/14
 * @project: order-service
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order/cart")
public class CartController {
  private static final Logger logger = LoggerFactory.getLogger(CartController.class);
  private final CartService cartService;

  @PostMapping("/add")
  public ResponseEntity<?> addCart(@Valid @RequestBody AddCartRequest addCartRequest) {
    logger.info("addCartRequest: {}", addCartRequest);

    try {
      cartService.add(addCartRequest.getCart());
      Collection<Cart> response = cartService.getAll(addCartRequest.getUserId());
      return ResponseEntity.ok(new SuccessResponse<>("Add cart successfully", HttpStatus.OK,
        response));
    } catch (Exception ex) {
      logger.error("addCartRequest ERROR: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse<>(ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
  }

  @GetMapping("/")
  public ResponseEntity<?> getCarts(@Valid @RequestParam("userId") Long userId) {
    logger.info("[getCarts] - userId: {}", userId);

    try {
      return ResponseEntity.ok(new SuccessResponse<>("Get carts successfully", HttpStatus.OK,
        cartService.getAll(userId)));
    } catch (Exception ex) {
      logger.error("getCarts ERROR: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse<>(ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
  }

  @PutMapping("/")
  public ResponseEntity<?> updateCart(@Valid @RequestBody UpdateCartRequest updateCartRequest) {
    logger.info("[updateCart] - updateCartRequest: {}", updateCartRequest);

    try {
      cartService.updateById(updateCartRequest.getCart());
      Collection<Cart> response = cartService.getAll(updateCartRequest.getCart().getUserId());
      return ResponseEntity.ok(new SuccessResponse<>("Add cart successfully", HttpStatus.OK,
        response));
    } catch (Exception ex) {
      logger.error("addCartRequest ERROR: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse<>(ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
  }

  @DeleteMapping("/remove")
  public ResponseEntity<?> deleteById(@Valid @RequestParam("cartId") Long cartId,
                                      @Valid @RequestParam("userId") Long userId) {
    logger.info("[deleteById] - cartId: {} - userId: {}", cartId, userId);

    try {
      cartService.deleteById(cartId);
      Collection<Cart> response = cartService.getAll(userId);
      return ResponseEntity.ok(new SuccessResponse<>("Remove cart successfully", HttpStatus.OK, response
      ));
    } catch (Exception ex) {
      logger.error("[deleteById] - ERROR: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse<>(ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
  }

  @DeleteMapping("/clear_all")
  public ResponseEntity<?> deleteByUserId(@Valid @RequestParam("userId") Long userId) {
    logger.info("[deleteByUserId] - cartId: {}", userId);

    try {
      cartService.deleteByUserId(userId);
      return ResponseEntity.ok(new SuccessResponse<>("Reset carts successfully", HttpStatus.OK, null
      ));
    } catch (Exception ex) {
      logger.error("[deleteById] - ERROR: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse<>(ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
  }
}

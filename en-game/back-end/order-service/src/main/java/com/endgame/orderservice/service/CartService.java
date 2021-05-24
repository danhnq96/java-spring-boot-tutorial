package com.endgame.orderservice.service;

import com.endgame.orderservice.entity.Cart;
import com.endgame.orderservice.payload.request.AddCartRequest;
import com.endgame.orderservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/14
 * @project: order-service
 */
@Service
@Transactional
public class CartService {
  private static final Logger logger = LoggerFactory.getLogger(CartService.class);

  @Autowired
  CartRepository cartRepository;

  public void add(Cart cart) throws Exception {
    logger.info("cart: {}", cart);
    Optional<Cart> optionalCart = cartRepository.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());

    if (optionalCart.isPresent()) {
      Cart currentCart = optionalCart.get();

      if (currentCart.getCartCount() >= currentCart.getAvailibilityCount()) {
        throw new Exception("Can't add cart because of run out of stock!");
      }

      currentCart.setCartCount(currentCart.getCartCount() + cart.getCartCount());

      cartRepository.save(currentCart);
    } else {
      cartRepository.save(cart);
    }
  }

  public Collection<Cart> getAll(Long userId) {
    logger.info("userId: {}", userId);
    return cartRepository.findAllByUserId(userId);
  }

  public void updateById(Cart cart) {
    Optional<Cart> optionalCart = cartRepository.findById(cart.getId());

    if (optionalCart.isPresent()) {
      Cart currentCart = optionalCart.get();
      currentCart.setCartCount(cart.getCartCount());

      cartRepository.save(currentCart);
    }
  }

  public void deleteById(Long cartId) {
    logger.info("[deleteById] - cartId: {}", cartId);
    cartRepository.deleteById(cartId);
  }

  public void deleteByUserId(Long userId) {
    logger.info("[deleteByUserId] - userId: {}", userId);
    cartRepository.deleteByUserId(userId);
  }
}

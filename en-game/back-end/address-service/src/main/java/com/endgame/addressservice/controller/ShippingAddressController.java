package com.endgame.addressservice.controller;

import com.endgame.addressservice.entity.ShippingAddress;
import com.endgame.addressservice.exception.BadRequestException;
import com.endgame.addressservice.exception.ResourceNotFoundException;
import com.endgame.addressservice.payload.request.ShippingAddressRequest;
import com.endgame.addressservice.service.ShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/16
 * @project: address-service
 */
@RestController
@RequestMapping("/api/address/shipping")
public class ShippingAddressController {

  private static final Logger logger = LoggerFactory.getLogger(ShippingAddressController.class);

  @Autowired
  ShippingAddressService shippingAddressService;

  @GetMapping("/user/{id}")
  public ShippingAddress getShippingAddressByUserId(@PathVariable long id) {
    logger.info("getShippingAddressByUserId - user id: " + id);

    return shippingAddressService.getByUserId(id).orElseThrow(() -> new ResourceNotFoundException("ShippingAddress",
      "id",
      id));
  }

  @GetMapping("/{id}")
  public ShippingAddress getShippingAddress(@PathVariable long id) {
    logger.info("getShippingAddress - shipping id: " + id);

    return shippingAddressService.getById(id).orElseThrow(() -> new ResourceNotFoundException("ShippingAddress", "id",
      id));
  }

  @PostMapping("/user")
  public ShippingAddress createShippingAddress(@Valid @RequestBody ShippingAddressRequest shippingAddressRequest) {
    return shippingAddressService.save(shippingAddressRequest).orElseThrow(() -> new BadRequestException("Create " +
      "shipping " +
      "error!"));
  }

  @PutMapping("/user")
  public ShippingAddress updateShippingAddress(@Valid @RequestBody ShippingAddressRequest shippingAddressRequest) {
    return shippingAddressService.save(shippingAddressRequest).orElseThrow(() -> new BadRequestException("Update " +
      "shipping " +
      "error!"));
  }
  
}

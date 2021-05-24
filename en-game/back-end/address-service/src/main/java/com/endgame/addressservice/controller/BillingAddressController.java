package com.endgame.addressservice.controller;

import com.endgame.addressservice.entity.BillingAddress;
import com.endgame.addressservice.exception.BadRequestException;
import com.endgame.addressservice.exception.ResourceNotFoundException;
import com.endgame.addressservice.payload.request.BillingAddressRequest;
import com.endgame.addressservice.service.BillingAddressService;
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
@RequestMapping("/api/address/billing")
public class BillingAddressController {

  private static final Logger logger = LoggerFactory.getLogger(BillingAddressController.class);

  @Autowired
  BillingAddressService billingAddressService;

  @GetMapping("/user/{id}")
  public BillingAddress getBillingAddressByUserId(@PathVariable long id) {
    logger.info("getBillingAddressByUserId - user id: " + id);

    return billingAddressService.getByUserId(id).orElseThrow(() -> new ResourceNotFoundException("BillingAddress", "id",
      id));
  }

  @GetMapping("/{id}")
  public BillingAddress getBillingAddress(@PathVariable long id) {
    logger.info("getBillingAddress - billing id: " + id);

    return billingAddressService.getById(id).orElseThrow(() -> new ResourceNotFoundException("BillingAddress", "id",
      id));
  }

  @PostMapping("/user")
  public BillingAddress createBillingAddress(@Valid @RequestBody BillingAddressRequest billingAddressRequest) {
    return billingAddressService.save(billingAddressRequest).orElseThrow(() -> new BadRequestException("Create " +
      "billing " +
      "error!"));
  }

  @PutMapping("/user")
  public BillingAddress updateBillingAddress(@Valid @RequestBody BillingAddressRequest billingAddressRequest) {
    return billingAddressService.update(billingAddressRequest).orElseThrow(() -> new BadRequestException("Update " +
      "billing " +
      "error!"));
  }
}

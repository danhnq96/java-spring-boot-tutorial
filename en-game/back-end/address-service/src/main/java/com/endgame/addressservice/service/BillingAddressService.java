package com.endgame.addressservice.service;

import com.endgame.addressservice.entity.BillingAddress;
import com.endgame.addressservice.payload.request.BillingAddressRequest;
import com.endgame.addressservice.repository.BillingAddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/16
 * @project: address-service
 */
@Service
public class BillingAddressService {

  @Autowired
  BillingAddressRepository billingAddressRepository;

  public Optional<BillingAddress> save(BillingAddressRequest billingAddressRequest) {
    ModelMapper modelMapper = new ModelMapper();

    BillingAddress billingAddress = modelMapper.map(billingAddressRequest, BillingAddress.class);

    return Optional.of(billingAddressRepository.save(billingAddress));
  }

  public Optional<BillingAddress> getById(long id) {
    return billingAddressRepository.findById(id);
  }

  public Optional<BillingAddress> getByUserId(long id) {
    return billingAddressRepository.findByUserId(id);
  }

  public Optional<BillingAddress> update(BillingAddressRequest billingAddressRequest) {
    BillingAddress billingAddress = new BillingAddress();
    ModelMapper modelMapper = new ModelMapper();
    Optional<BillingAddress> result =
      billingAddressRepository.findByUserId(billingAddressRequest.getUserId());

    if (result.isPresent()) {
      billingAddress = result.get();
      modelMapper.map(billingAddressRequest, billingAddress);

      return Optional.of(billingAddressRepository.save(billingAddress));
    }

    return Optional.of(billingAddress);
  }
}

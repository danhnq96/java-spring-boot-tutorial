package com.endgame.addressservice.service;

import com.endgame.addressservice.entity.ShippingAddress;
import com.endgame.addressservice.payload.request.ShippingAddressRequest;
import com.endgame.addressservice.repository.ShippingAddressRepository;
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
public class ShippingAddressService {

  @Autowired
  ShippingAddressRepository shippingAddressRepository;

  public Optional<ShippingAddress> save(ShippingAddressRequest shippingAddressRequest) {
    ModelMapper modelMapper = new ModelMapper();

    ShippingAddress shippingAddress = modelMapper.map(shippingAddressRequest, ShippingAddress.class);

    return Optional.of(shippingAddressRepository.save(shippingAddress));
  }

  public Optional<ShippingAddress> getById(long id) {
    return shippingAddressRepository.findById(id);
  }

  public Optional<ShippingAddress> getByUserId(long id) {
    return shippingAddressRepository.findByUserId(id);
  }

  public Optional<ShippingAddress> update(ShippingAddressRequest shippingAddressRequest) {
    ShippingAddress shippingAddress = new ShippingAddress();
    ModelMapper modelMapper = new ModelMapper();
    Optional<ShippingAddress> result =
      shippingAddressRepository.findByUserId(shippingAddressRequest.getUserId());

    if (result.isPresent()) {
      shippingAddress = result.get();
      modelMapper.map(shippingAddressRequest, shippingAddress);

      return Optional.of(shippingAddressRepository.save(shippingAddress));
    }

    return Optional.of(shippingAddress);
  }
}

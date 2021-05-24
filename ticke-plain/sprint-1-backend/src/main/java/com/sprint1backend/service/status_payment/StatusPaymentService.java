package com.sprint1backend.service.status_payment;

import com.sprint1backend.entity.StatusPayment;

import java.util.List;

public interface StatusPaymentService {
    void save(StatusPayment statusPayment);

    void edit(StatusPayment statusPayment);

    void delete(Long id);

    StatusPayment findById(Long id);
    StatusPayment findByName(String name);

    List<StatusPayment> findAll();

}

package com.sprint1backend.repository;

import com.sprint1backend.entity.StatusPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatusPaymentRepository extends JpaRepository<StatusPayment, Long> {
    StatusPayment findByName(String name);
}

package com.sprint1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint1backend.entity.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query(value = "select * from invoice where user_id = ?", nativeQuery = true)
    List<Invoice> findAllByAppUserId(Long id);
}

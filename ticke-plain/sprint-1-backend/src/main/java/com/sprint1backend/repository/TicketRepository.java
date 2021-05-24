package com.sprint1backend.repository;

import com.sprint1backend.entity.AppUser;
import com.sprint1backend.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.sprint1backend.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByStatusPayment_Name(String statusPaymentName);
    @Query(value = "select * from ticket where invoice_id = ?;",nativeQuery = true)
    List<Ticket>findAllByInvoiceId(Long id);
    Page<Ticket> findAllByAppUser(AppUser appUser,Pageable pageable);
    List<Ticket> findAllByPassenger_FullNameAndBooking_BookingCode(String bookingCode, String fullNamePassenger);

}

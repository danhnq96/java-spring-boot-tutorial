package com.sprint1backend.repository;

import com.sprint1backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingCode(String bookingCode);

    Booking findBookingByBookingCodeContaining(String bookingCode);
}

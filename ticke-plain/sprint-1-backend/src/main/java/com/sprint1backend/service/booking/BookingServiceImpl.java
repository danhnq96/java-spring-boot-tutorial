package com.sprint1backend.service.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1backend.entity.Booking;
import com.sprint1backend.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void saveBooking(Booking booking) {
        this.bookingRepository.save(booking);
    }

    @Override
    public Booking findBookingByBookingCode(String bookingCode) {
        return this.bookingRepository.findByBookingCode(bookingCode);
    }
}

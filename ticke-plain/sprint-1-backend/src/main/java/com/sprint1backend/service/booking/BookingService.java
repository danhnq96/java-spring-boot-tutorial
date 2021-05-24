package com.sprint1backend.service.booking;

import com.sprint1backend.entity.Booking;

public interface BookingService {
    void saveBooking(Booking booking);

    Booking findBookingByBookingCode(String bookingCode);
}

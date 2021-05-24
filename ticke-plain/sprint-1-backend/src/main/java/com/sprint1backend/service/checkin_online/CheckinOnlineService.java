package com.sprint1backend.service.checkin_online;

import com.sprint1backend.entity.Ticket;

import java.util.List;

public interface CheckinOnlineService {
    List<Ticket> findAllByPassenger_FullNameAndBooking_BookingCode(String bookingCode, String fullNamePassenger);
}

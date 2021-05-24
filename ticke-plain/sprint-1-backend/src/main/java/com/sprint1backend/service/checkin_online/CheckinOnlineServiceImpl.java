package com.sprint1backend.service.checkin_online;

import com.sprint1backend.entity.Ticket;
import com.sprint1backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinOnlineServiceImpl implements CheckinOnlineService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllByPassenger_FullNameAndBooking_BookingCode(String bookingCode, String fullNamePassenger) {
        return this.ticketRepository.findAllByPassenger_FullNameAndBooking_BookingCode
                (bookingCode, fullNamePassenger);
    }
}

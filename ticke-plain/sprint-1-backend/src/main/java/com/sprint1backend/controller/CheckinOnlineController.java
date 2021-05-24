package com.sprint1backend.controller;

import com.sprint1backend.entity.Ticket;
import com.sprint1backend.service.checkin_online.CheckinOnlineService;
import com.sprint1backend.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkinOnline")
@CrossOrigin
public class CheckinOnlineController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CheckinOnlineService checkinOnlineService;

    @GetMapping("/findInfoFlight/{fullName}&{bookingCode}")
    public ResponseEntity<List<Ticket>> getCheckOnline(@PathVariable("fullName") String fullName, @PathVariable("bookingCode") String bookingCode) {
        String fullName1 = fullName.trim();
        String bookingCode1 = bookingCode.trim();
        List<Ticket> ticketList = this.checkinOnlineService.findAllByPassenger_FullNameAndBooking_BookingCode(fullName1, bookingCode1);
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @GetMapping("/checkStatusCheckin")
    public ResponseEntity<Void> bookingOnline(@RequestParam("idCheckin") String idCheckin) {
        String[] listId = idCheckin.split("-#-");
        Ticket ticket = null;

        for (int i = 0; i < listId.length; i++) {
            ticket = ticketService.findTicketByID(Long.parseLong(listId[i]));
            if (ticket == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            ticket.setStatusCheckin(true);
            ticketService.save(ticket);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

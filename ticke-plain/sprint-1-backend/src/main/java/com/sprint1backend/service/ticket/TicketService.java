package com.sprint1backend.service.ticket;

import com.sprint1backend.entity.DAOTicket;
import com.sprint1backend.entity.Ticket;
import com.sprint1backend.model.TicketDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketService {
    String saveTicket(TicketDTO ticketDTO, Long idFlightDeparture, Long idFlightArrival);

    String editTicket(TicketDTO ticketDTO, String passengerNameEdit, String emailEdit);

    String deleteTicket(Long id);

    Ticket findTicketByID(Long id);

    List<Ticket> findAllTicket();

    List<Ticket> findAllByStatusPaymentName(String statusPaymentName);

    List<Ticket> searchTicket(String statusPaymentName, String searchBy, String searchValue);

    void save(Ticket ticket);

    List<Ticket>findAllListTicketByInvoiceId(Long id);

    void addTicketAndBooking (DAOTicket daoTicket);
    Page<Ticket> findAllByAppUser(Long idAppAccount, int pageable);
}

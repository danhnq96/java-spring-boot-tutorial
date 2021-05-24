package com.sprint1backend.controller;

import com.sprint1backend.entity.*;
import com.sprint1backend.model.MessageDTO;
import com.sprint1backend.model.TicketDTO;
import com.sprint1backend.model.TicketStatusPaymentDTO;
import com.sprint1backend.service.account.AccountService;
import com.sprint1backend.service.flight_information.FlightInformationService;
import com.sprint1backend.service.status_payment.StatusPaymentService;
import com.sprint1backend.service.ticket.TicketService;
import com.sprint1backend.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketRestController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightInformationService flightInformationService;

    @Autowired
    private StatusPaymentService statusPaymentService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    AccountService appAccountService;

    @Autowired
    private JavaMailSender mailSender;

    /*
     * get all ticket in database
     * @param Nothing
     * @return ResponseEntity<List<Ticket>>
     * */
    @GetMapping("/list")
    public ResponseEntity<List<Ticket>> getListTicket() {
        List<Ticket> ticketList = this.ticketService.findAllTicket();
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    /* *
     * Chau start
     * */

    /*
     * get flight information by id
     * @param idFlightInformation
     * @return ResponseEntity<FlightInformation>
     * */
    @GetMapping("/find-flight-information-by-id/{id}")
    public ResponseEntity<FlightInformation> getFlightInformationByID(@PathVariable Long id) {
        FlightInformation flightInformation = this.flightInformationService.findFlightInformationByID(id);
        return new ResponseEntity<>(flightInformation, HttpStatus.OK);
    }

    /*
     * get list app user by email
     * @param email
     * @return ResponseEntity<List<AppUser>>
     * */
    @GetMapping("/app-user/{emailInput}")
    public ResponseEntity<List<AppUser>> searchAppUserByEmail(@PathVariable String emailInput) {
        List<AppUser> appUserList = this.appUserService.testEmailOfAppUser(emailInput);
        return new ResponseEntity<>(appUserList, HttpStatus.OK);
    }

    @PutMapping("/edit/{ticket}/{passengerEdit}/{appUserID}")
    public ResponseEntity<Ticket> editTicket(@PathVariable TicketDTO ticket,
                                             @PathVariable String passengerEdit,
                                             @PathVariable String appUserID) {

        this.ticketService.editTicket(ticket, passengerEdit, appUserID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
    /*
     * get ticket by id
     * @param idTicket
     * @return ResponseEntity<Ticket>
     * */
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Ticket> getTicketByID(@PathVariable Long id) {
        Ticket ticket = this.ticketService.findTicketByID(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    /*
     * delete ticket by id
     * @param idTicket
     * @return ResponseEntity<MessageDTO>
     * */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDTO> deleteTicketByID(@PathVariable Long id) {
        String message = this.ticketService.deleteTicket(id);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    /*
     * edit ticket
     * @param ticketOld, passengerNameEdit, emailEdit
     * @return ResponseEntity<MessageDTO>
     * */
//    @PutMapping("/edit/{ticket}/{passengerNameEdit}/{emailEdit}")
//    public ResponseEntity<MessageDTO> editTicket(@PathVariable Ticket ticket,
//                                             @PathVariable String passengerNameEdit,
//                                             @PathVariable String emailEdit) {
//        String message = this.ticketService.editTicket(ticket, passengerNameEdit, emailEdit);
//        return ResponseEntity.ok(new MessageDTO(message));
//    }

    /*
     * save ticket
     * @param idFlightDeparture, idFlightArrival, ticketDTO
     * @return ResponseEntity<MessageDTO>
     * */
    @PostMapping("/save/{idFlightDeparture}/{idFlightArrival}")
    public ResponseEntity<MessageDTO> saveTicket(@PathVariable Long idFlightDeparture,
                                                 @PathVariable Long idFlightArrival,
                                                 @RequestBody TicketDTO ticketDTO) {
        String message = this.ticketService.saveTicket(ticketDTO, idFlightDeparture, idFlightArrival);
        return new ResponseEntity<>(new MessageDTO(message), HttpStatus.OK);
    }

    /* *
     * Chau end
     * */



    /*
     * Đăng start
     * */

    /*
     * get List Ticket by Status Payment
     * @param statusPaymentName
     * @return ResponseEntity<List<Ticket>>
     * @throw
     * */
    @GetMapping("/list/{statusPaymentName}")
    public ResponseEntity<List<Ticket>> findAllTicketByStatusPayment(@PathVariable String statusPaymentName) {
        List<Ticket> ticketList = this.ticketService.findAllByStatusPaymentName(statusPaymentName);
        return ticketList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(ticketList, HttpStatus.OK);
    }
//
    /*
     * get List Ticket by Status Payment and another filter (Booking Code or Phone Number)
     * @param statusPaymentName, searchBy, searchValue
     * @return ResponseEntity<List<Ticket>>
     * @throw
     * */
    @GetMapping("/search")
    public ResponseEntity<List<Ticket>> searchTicket(@RequestParam String statusPaymentName, @RequestParam String searchBy,
            @RequestParam String searchValue
    ) {
        List<Ticket> ticketList = this.ticketService.searchTicket(statusPaymentName, searchBy, searchValue);
        return ticketList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(ticketList, HttpStatus.OK);
    }


    /*
     * Edit Status Payment of Ticket
     * @param id, ticketStatusPaymentDTO
     * @return ResponseEntity<Ticket>
     * @throw
     *
     * */
    @PutMapping("/set-status-payment/{id}")
    public ResponseEntity<Ticket> setStatusPayment(@PathVariable Long id,
                                                   @RequestBody TicketStatusPaymentDTO ticketStatusPaymentDTO) {
        Ticket ticket = this.ticketService.findTicketByID(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        StatusPayment statusPayment = this.statusPaymentService.findByName(
                ticketStatusPaymentDTO.getStatusPaymentName());
        if (statusPayment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticket.setStatusPayment(statusPayment);
        this.ticketService.save(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
    /*
     * Đăng end
     * */

    // Din
    @PostMapping("/add")
    public ResponseEntity<Void> addTicketAndBooking(@RequestBody DAOTicket daoTicket) {
        System.out.println(daoTicket.getCountArrival() + daoTicket.getCountDeparture());
        this.ticketService.addTicketAndBooking(daoTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-ticket-app-user/{idAccount}/{page}")
    public ResponseEntity<Page<Ticket>> findTicketByAppUser(@PathVariable Long idAccount, @PathVariable int page) {
        System.out.println(this.ticketService.findAllByAppUser(idAccount, page));
        return new ResponseEntity<>(this.ticketService.findAllByAppUser(idAccount, page),HttpStatus.OK);
    }
    // Khanh
    @GetMapping("/findAllTicketByInvoiceId/{idInvoice}")
    public ResponseEntity<List<Ticket>> findAllTicket(@PathVariable Long idInvoice) {
        List<Ticket> ticketList = this.ticketService.findAllListTicketByInvoiceId(idInvoice);
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

//    Đăng
@GetMapping("/sent-mail")
public ResponseEntity<Void> sendMail(@RequestParam("idAccount") Long idAccount
                                     ) throws UnsupportedEncodingException, MessagingException {
    AppUser appUser = this.appAccountService.findByIdAppAccount(idAccount).getAppUser();
    String subject = "Thông báo mua vé thành công tại CO6 Airline";
    String senderName = "C06 Airline";
    String mailContent = "<p>Dear " + appUser.getFullName() + ",</p>";
    mailContent += "<p>Chúc mừng bạn đã mua vé thành công. Cám ơn bạn đã sử dụng dịch vụ của chúng tôi</p>";
//
//        String verifyURL = siteURL + "/verify?code=" + newUser.getVerificationCode();
//        mailContent += "<a href=\"" + verifyURL + "\">Xác nhận</a>";

    mailContent += "<p >Trân trọng, C06 Airline Team</p>";

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message,true, "utf-8");

    helper.setFrom("kaelboom92@gmail.com", senderName);
    helper.setTo(appUser.getEmail());
    helper.setSubject(subject);
    helper.setText(mailContent, true);

    mailSender.send(message);
    return new ResponseEntity<>(HttpStatus.OK);
//
}
}

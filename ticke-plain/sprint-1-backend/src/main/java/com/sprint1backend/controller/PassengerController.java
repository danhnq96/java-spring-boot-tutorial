package com.sprint1backend.controller;

import com.sprint1backend.entity.AppUser;
import com.sprint1backend.entity.Passenger;
import com.sprint1backend.service.account.AccountService;
import com.sprint1backend.service.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/rest")
@CrossOrigin
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    @Autowired
    AccountService appAccountService;

    @Autowired
    private JavaMailSender mailSender;
    // Din
    @PostMapping(value = "/passengers-form-array")
    public ResponseEntity<Void> addPassengerArrayList(@RequestBody Passenger passenger) {
        this.passengerService.savePassengerListPassenger(passenger);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sent-mail")
    public ResponseEntity<Void> sendMail(@RequestParam("idAccount") Long idAccount,
                                         @RequestParam String bookingCode) throws UnsupportedEncodingException, MessagingException {
        AppUser appUser = this.appAccountService.findByIdAppAccount(idAccount).getAppUser();
        String subject = "Thông báo bạn đã đặt vé tại CO6 Airline";
        String senderName = "C06 Airline";
        String mailContent = "<p>Dear " + appUser.getFullName() + ",</p>";
        mailContent += "<p>Mã đặt chỗ của bạn là: <b>"+bookingCode+"</b></p>";
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

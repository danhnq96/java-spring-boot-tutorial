package com.sprint1backend.controller;

import com.sprint1backend.entity.FlightInformation;
import com.sprint1backend.entity.Promotion;
import com.sprint1backend.service.email.EmailService;
import com.sprint1backend.service.promotion_code.PromotionCodeSeriveImpl;
import com.sprint1backend.service.promotion_code.PromotionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotionCode")
@CrossOrigin
public class PromotionCodeController {

    @Autowired
    private PromotionCodeService promotionCodeSerive;
    @Autowired
    private EmailService emailService;

    @GetMapping("/list")
    public ResponseEntity<List<Promotion>> getListPromotionCode() {
        List<Promotion> fillAllPromotion = this.promotionCodeSerive.fillAllPromotion();
        if (fillAllPromotion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(fillAllPromotion, HttpStatus.OK);
        }

    }

    @GetMapping("/sendEmail")
    public ResponseEntity<Void> getSendEmail(@RequestParam(value = "emailPassages") String email) {

        SimpleMailMessage sendEmail = new SimpleMailMessage();
        sendEmail.setFrom("khanh06111993@gmail.com");
        sendEmail.setTo(email);
        sendEmail.setSubject("Hoang Bue");
        sendEmail.setText(" http://localhost:4200/promotion-code");
        emailService.sendEmail(sendEmail);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}

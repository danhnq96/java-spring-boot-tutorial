package com.sprint1backend.controller;

import com.sprint1backend.entity.Feedback;
import com.sprint1backend.service.feedback.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackRestController {

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    JavaMailSender mailSender;

    @PostMapping("/send-feedback")
    public ResponseEntity<Void> sendFeedBack(@RequestBody Feedback feedback) {
        feedback.setSendDate(LocalDate.now());
        feedback.setProcessStatus(false);
        if (feedback.getContent() != null && feedback.getTitle() != null) {
            this.feedBackService.sendFeedback(feedback);
        }
        SimpleMailMessage sendFeedback = new SimpleMailMessage();
        sendFeedback.setFrom("c0620g1@gmail.com");
        sendFeedback.setTo(feedback.getSenderEmail());
        sendFeedback.setSubject("C0620G1 Airline");
        sendFeedback.setText(feedback.getSenderName() + " thân mến:\n" +
                "\n" +
                "Cảm ơn bạn đã gởi phản hồi nhận xét về vấn đề " + feedback.getTitle() + " của sản phẩm về chúng tôi." +
                "\n" +
                "Bên chúng tôi sẽ xem xét và xử lý yêu cầu của bạn trong thời gian sớm nhất." +
                "\n" +
                "Một lần nữa, cảm ơn vì yêu cầu của quý khách.\n" +
                "\n" +
                "Trân trọng.");
        mailSender.send(sendFeedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

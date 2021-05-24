package com.endgame.apigateway.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/20
 * @project: order-service
 */
@Service
public class SendEmail {
  @Autowired
  private JavaMailSender emailSender;

  public void sendHTMLEmail(String sendTo, String template) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

    message.setContent(template, "text/html");
    helper.setTo(sendTo);
    helper.setSubject("Test send HTML email");

    emailSender.send(message);
  }
}

package com.endgame.adminservice.sevices;

public interface MailService {
    void sendEmail(String subject,String to, String content);
}

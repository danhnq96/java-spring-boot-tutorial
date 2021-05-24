package com.sprint1backend.service.email;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    public void sendEmail(SimpleMailMessage email);
}

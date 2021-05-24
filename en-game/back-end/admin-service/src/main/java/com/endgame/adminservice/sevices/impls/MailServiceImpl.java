package com.endgame.adminservice.sevices.impls;

import com.endgame.adminservice.sevices.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

    private static final String SEND_GRID_ENDPOINT_SEND_EMAIL = "mail/send";
    @Autowired
    private JavaMailSender mailSender;
    @Value("${send_grid.api_key}")
    private String sendGridApiKey;
    @Value("${send_grid.from_email}")
    private String sendGridFromEmail;
    @Value("${send_grid.from_name}")
    private String sendGridFromName;

//    @Override
//    synchronized public void sendEmail(String subject, String toEmail, String contentEmail) {
//        Email from = new Email(sendGridFromEmail);
//        from.setName(sendGridFromName);
//        Email to = new Email(toEmail);
//        Content content = new Content(CONTENT_TYPE_TEXT_PLAIN, contentEmail);
//        Mail mail = new Mail(from, subject, to, content);
//        SendGrid sg = new SendGrid(sendGridApiKey);
//        Request request = new Request();
//        try {
//            request.setMethod(Method.POST);
//            request.setEndpoint(SEND_GRID_ENDPOINT_SEND_EMAIL);
//            request.setBody(mail.build());
//            Response response = sg.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    @Override
    public void sendEmail(String subject, String toEmail, String contentEmail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject(subject);
        msg.setText(contentEmail);
        try {
            mailSender.send(msg);
        } catch (MailSendException e){
            System.out.println(e.getMessage());
        }
    }
}

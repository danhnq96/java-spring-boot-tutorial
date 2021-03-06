package com.sprint2.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.services.pay.PaySerVice;

@Configuration
@EnableScheduling
public class AutoSendMailForCustomer {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private PaySerVice paySerVice;

    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Scheduled(fixedDelay = 1000 * 3600 * 24)
    private void scheduleFixedDelayTask() throws InterruptedException {
        System.out.println("System auto send mail start.");
        List<MemberCard> allMemberCard = this.paySerVice.findAll();
        List<MemberCard> listMemberCardNearExpired = new ArrayList<>();
        HashSet<String> mailList = new HashSet<>();
        LocalDateTime now = LocalDateTime.now();

        if (!allMemberCard.isEmpty()) {
            filterMemberCard(allMemberCard, listMemberCardNearExpired, mailList, now);
            if (!mailList.isEmpty()) {
                sendMail(listMemberCardNearExpired, mailList);
            } else {
                System.out.println("List member card near expired is empty.");
            }
        } else {
            System.out.println("List member card is empty.");
        }
    }

    private void filterMemberCard(List<MemberCard> allMemberCard, List<MemberCard> listMemberCardNearExpired,
                                  HashSet<String> mailList, LocalDateTime now) {
        for (MemberCard memberCard : allMemberCard) {
            LocalDateTime endDate = memberCard.getEndDate();
            Duration between = Duration.between(now, endDate);
            if (between.toHours() <= 72 && between.toHours() > 0) {
                listMemberCardNearExpired.add(memberCard);
                mailList.add(memberCard.getCar().getCustomer().getEmail());
            }
        }
        System.out.println(mailList);
    }

    private void sendMail(List<MemberCard> listMemberCardNearExpired,
                          HashSet<String> mailList) throws InterruptedException {
        try {
            for (String mail : mailList) {
                List<MemberCard> memberCardOfMail = new ArrayList<>();
                for (MemberCard memberCard : listMemberCardNearExpired) {
                    if (memberCard.getCar().getCustomer().getEmail().equals(mail)) {
                        memberCardOfMail.add(memberCard);
                    }
                }
                createMail(mail, memberCardOfMail);
            }
        } catch (RuntimeException run) {
            Thread.sleep(300000);
            scheduleFixedDelayTask();
        }
    }

    private void createMail(String mail, List<MemberCard> memberCardOfMail) {
        try {
            MimeMessage message = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String nameCustomer = memberCardOfMail.get(0).getCar().getCustomer().getFullName();

            helper.setTo(mail);
            helper.setSubject("Th??ng b??o gia h???n v?? xe");
            StringBuilder mailContent = new StringBuilder(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "  <meta charset=\"UTF-8\">\n" +
                            "  <title>Mail</title>\n" +
                            "<link rel=\"stylesheet\" " +
                            "href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\">" +
                            "  <style>\n" +
                            "    * {\n" +
                            "      font-family: \"Varela Round\";\n" +
                            "    }" +
                            "    .bodyMail {\n" +
                            "      margin-top: 1%;\n" +
                            "    }\n" +
                            "\n" +
                            "    h5 {\n" +
                            "      color: white;\n" +
                            "      background-color: green;\n" +
                            "      text-align: center\n" +
                            "    }\n" +
                            "\n" +
                            "    p {\n" +
                            "      margin: 1% 0;\n" +
                            "    }\n" +
                            "\n" +
                            "    table {\n" +
                            "      border: 1px solid;\n" +
                            "      border-collapse: separate;\n" +
                            "      width: 100%\n" +
                            "    }\n" +
                            "\n" +
                            "    th {\n" +
                            "      border: 1px solid;\n" +
                            "      text-align: center;\n" +
                            "    }\n" +
                            "    td {\n" +
                            "      border: 1px solid;\n" +
                            "      text-align: left;\n" +
                            "    }\n" +
                            "\n" +
                            "    span {\n" +
                            "      color: blue;\n" +
                            "    }\n" +
                            "\n" +
                            "    .autoMail {\n" +
                            "      color: red;\n" +
                            "    }\n" +
                            "  </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<div class=\"container-fluid\">\n" +
                            "  <div class=\"row\">\n" +
                            "    <div class=\"col-sm-3\"></div>\n" +
                            "    <div class=\"col-sm-6 bodyMail\">\n" +
                            "      <h5 style=\"width: 100%\">\n" +
                            "        C??ng ty TNHH C06Parking th??ng b??o v?? xe s???p h???t h???n\n" +
                            "      </h5>\n" +
                            "<div>\n" +
                            "        <img src=\"https://bkaii.com.vn/images/bai-do-xe-tai-duc.jpg" +
                            "\" style=\"width: 100%; height: 425px\">\n" +
                            "      </div>" +
                            "      <p>K??nh g???i qu?? kh??ch: <span>" + nameCustomer + "</span></p>\n" +
                            "      <p>Sau ????y l?? danh s??ch v?? xe s???p h???t h???n c???a qu?? kh??ch : </p>\n" +
                            "      <div class=\"row\">\n" +
                            "        <div class=\"container-xl\">\n" +
                            "          <div class=\"table-responsive\">\n" +
                            "            <div class=\"table-wrapper\">\n" +
                            "              <table class=\"table table-striped\">\n" +
                            "                <tr style=\"background-color: rgba(0,123,255,0.67)\">\n" +
                            "                  <th>H??ng xe</th>\n" +
                            "                  <th>Bi???n s??? xe</th>\n" +
                            "                  <th>Lo???i v??</th>\n" +
                            "                  <th>Ng??y h???t h???n</th>\n" +
                            "                  <th>Gi?? (VN??)</th>\n" +
                            "                </tr>\n");
            for (MemberCard memberCard : memberCardOfMail) {
                mailContent.append("<tr>\n");
                mailContent.append("<td>");
                mailContent.append(memberCard.getCar().getBrandName());
                mailContent.append("</td>");
                mailContent.append("<td>");
                mailContent.append(memberCard.getCar().getPlateNumber());
                mailContent.append("</td>");
                mailContent.append("<td>");
                mailContent.append(memberCard.getMemberCardType().getMemberTypeName());
                mailContent.append("</td>");
                mailContent.append("<td>");
                mailContent.append(memberCard.getEndDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                mailContent.append("</td>");
                mailContent.append("<td style=\"text-align: right;\">");
                mailContent.append(memberCard.getPrice());
                mailContent.append("</td>");
                mailContent.append("</tr>");
            }

            mailContent.append("</table>\n" +
                    "            </div>\n" +
                    "          </div>\n" +
                    "        </div>\n" +
                    "      </div>\n" +
                    "      <p>Qu?? kh??ch vui l??ng thanh to??n tr?????c khi th???i h???n v?? k???t th??c! " +
                    "N???u kh??ng thanh to??n v?? s??? ???????c h???y\n" +
                    "        theo quy ?????nh c???a c??ng ty.</p>\n" +
                    "      <p>Click v??o <a style=\"color: green\" " +
                    "href=\"http://localhost:4200\">????y</a> ????? ????ng nh???p nhanh v??o h??? th???ng " +
                    "c???a ch??ng t??i.</p>\n" +
                    "      <p class=\"autoMail\">P/s : ????y l?? th?? th??ng b??o t??? ?????ng. " +
                    "Qu?? kh??ch vui l??ng kh??ng tr??? l???i th?? n??y!</p>\n" +
                    "      <div class=\"navbar-light bg-light mt-5 rounded\">\n" +
                    "        <div class=\"row p-4  mx-0 p-0\">\n" +
                    "          <div class=\"col-sm-3 col-xl-3\">\n" +
                    "            <h4 class=\"justify-content-center\">LI??N H???</h4>\n" +
                    "            <ul class=\"list-unstyled\">\n" +
                    "              <li>295 Nguy???n T???t Th??nh, DN</li>\n" +
                    "              <li>0236 6517 021</li>\n" +
                    "            </ul>\n" +
                    "          </div>\n" +
                    "          <div class=\"col-sm-5 col-xl-5 \">\n" +
                    "            <h4 class=\"justify-content-center\">GI???I THI???U</h4>\n" +
                    "            <p>C??ng ty ch??ng t??i h??n h???nh mang ?????n nh???ng d???ch v??? ?????u xe hi???n ?????i " +
                    "v?? an to??n nh???t.\n" +
                    "              Ch??ng t??i t??? h??o l?? 1 trong nh???ng h??? th???ng ?????t ch??? ?????u xe</p>" +
                    "<p>h??ng ?????u Vi???t Nam.</p>\n" +
                    "          </div>\n" +
                    "        </div>\n" +
                    "      </div>" +
                    "    </div>\n" +
                    "    <div class=\"col-sm-3\"></div>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n");

            helper.setText(String.valueOf(mailContent), true);
            this.emailSender.send(message);
        } catch (MessagingException messaging) {
            messaging.getStackTrace();
        }
    }
}

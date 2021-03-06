package com.sprint2.backend.services.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.entity.Invoice;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.repository.InvoiceRepository;
import com.sprint2.backend.repository.MemberCardRepository;

@Service
public class PayServiceImpl implements PaySerVice {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private MemberCardRepository memberCardRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    /*
     * get all member card in database
     * @param nothing
     * @return List<MemberCard>
     * */
    @Override
    public List<MemberCard> findAll() {
        return this.memberCardRepository.findAll();
    }

    /*
     * get all member card of customer currently logged in database
     * @param idCustomer
     * @return List<MemberCard>
     * */
    @Override
    public List<MemberCard> findByCustomerID(Long id) {
        return this.memberCardRepository.findByCustomerId(id);
    }

    /*
     * update member card after customer pay and send mail notification for customer
     * @param money, memberCardList
     * void
     * */
    @Override
    public void updateMemberCardAndSendMailAfterCustomerPay(Double money, List<Long> listIDMemberCard) {

        // update member card and create list member card for send email :
        List<MemberCard> memberCardListAfterPay = updateMemberCard(money, listIDMemberCard);

        // create invoice :
        createInvoice(money, listIDMemberCard);

        // send mail notification for customer after pay complete :
        sendMailNotificationForCustomer(memberCardListAfterPay);
    }

    private List<MemberCard> updateMemberCard(Double money, List<Long> listIDMemberCard) {
        List<MemberCard> memberCardListAfterPay = new ArrayList<>();

        for (Long element : listIDMemberCard) {
            MemberCard memberCard = this.memberCardRepository.findById(element).orElse(null);
            if (memberCard != null) {
                memberCardListAfterPay.add(memberCard);
                if (memberCard.getMemberCardType().getMemberTypeName().equals("Tu???n")) {
                    memberCard.setEndDate(memberCard.getEndDate().plusDays(7));
                } else if (memberCard.getMemberCardType().getMemberTypeName().equals("Th??ng")) {
                    memberCard.setEndDate(memberCard.getEndDate().plusMonths(1));
                } else {
                    memberCard.setEndDate(memberCard.getEndDate().plusYears(1));
                }
                this.memberCardRepository.save(memberCard);
            }
        }

        return memberCardListAfterPay;
    }

    private void createInvoice(Double money, List<Long> listIDMemberCard) {
        MemberCard memberCardUseCreatInvoice =
                this.memberCardRepository.findById(listIDMemberCard.get(0)).orElse(null);
        if (memberCardUseCreatInvoice != null) {
            Invoice invoice = new Invoice();
            Customer customer = memberCardUseCreatInvoice.getCar().getCustomer();
            invoice.setTotalAmount(money);
            invoice.setCustomer(customer);
            invoice.setPayDate(LocalDateTime.now());
            this.invoiceRepository.save(invoice);
        }
    }

    private void sendMailNotificationForCustomer(List<MemberCard> memberCardListAfterPay) {
        try {
            MimeMessage message = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String nameCustomer = memberCardListAfterPay.get(0).getCar().getCustomer().getFullName();

            helper.setTo(memberCardListAfterPay.get(0).getCar().getCustomer().getEmail());
            helper.setSubject("Th??ng b??o t??? C06Parking");
            StringBuilder mailContent = new StringBuilder(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "  <meta charset=\"UTF-8\">\n" +
                            "  <title>Mail</title>\n" +
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
                            "      width: 50%\n" +
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
                            "        C??ng ty TNHH C06Parking th??ng b??o gia h???n v?? xe th??nh c??ng\n" +
                            "      </h5>\n" +
                            "<div>\n" +
                            "        <img src=\"https://bkaii.com.vn/images/bai-do-xe-tai-duc.jpg" +
                            "\" style=\"width: 100%; height: 425px\">\n" +
                            "      </div>" +
                            "      <p>K??nh g???i qu?? kh??ch: <span>" + nameCustomer + "</span></p>\n" +
                            "      <p>Sau ????y l?? danh s??ch v?? xe ???? gia h???n th??nh c??ng c???a qu?? kh??ch : </p>\n" +
                            "      <div class=\"row\">\n" +
                            "        <div class=\"container-xl\">\n" +
                            "          <div class=\"table-responsive\">\n" +
                            "            <div class=\"table-wrapper\">\n" +
                            "              <table>\n" +
                            "                <tr style=\"background-color: rgba(0,123,255,0.67)\">\n" +
                            "                  <th>Bi???n s??? xe</th>\n" +
                            "                  <th>Lo???i v??</th>\n" +
                            "                  <th>Ng??y h???t h???n sau khi c???p nh???t</th>\n" +
                            "                </tr>\n");
            for (MemberCard memberCardAfterPay : memberCardListAfterPay) {
                mailContent.append("<tr>\n");
                mailContent.append("<td>");
                mailContent.append(memberCardAfterPay.getCar().getPlateNumber());
                mailContent.append("</td>");
                mailContent.append("<td>");
                mailContent.append(memberCardAfterPay.getMemberCardType().getMemberTypeName());
                mailContent.append("</td>");
                mailContent.append("<td>");
                mailContent.append(memberCardAfterPay.getEndDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                mailContent.append("</td>");
                mailContent.append("</tr>");
            }

            mailContent.append("</table>\n" +
                    "            </div>\n" +
                    "          </div>\n" +
                    "        </div>\n" +
                    "      </div>\n" +
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
            messaging.printStackTrace();
        }
    }
}

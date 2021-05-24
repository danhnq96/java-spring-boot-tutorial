package com.endgame.adminservice.commons;

import com.endgame.adminservice.sevices.MailService;
import com.endgame.adminservice.dto.employee.EmployeeDTO;

public class SendGridThread  extends Thread {
    private String threadName;
    private EmployeeDTO employeeDTO;
    private String pass;
    private MailService mailService;

    public SendGridThread() {
    }

    public SendGridThread(String threadName, EmployeeDTO employeeDTO, String pass, MailService mailService) {
        this.threadName = threadName;
        this.employeeDTO = employeeDTO;
        this.pass = pass;
        this.mailService = mailService;
    }

    public void run() {
        mailService.sendEmail("End Game Project", employeeDTO.getEmail(), "Your Account\nUsername: " + employeeDTO.getAccount().getUsername() + "\nPassword: " + pass);
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

package com.sprint1backend.controller;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.Employee;
import com.sprint1backend.model.ChangePasswordEmployeeDTO;
import com.sprint1backend.service.account.AccountService;
import com.sprint1backend.service.email.EmailService;
import com.sprint1backend.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PasswordController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AccountService appAccountService;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* Create By : Quoc_NT
     *  */
    @CrossOrigin
    @RequestMapping(value = "/sendEmail/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<Void> sendMail(@PathVariable(value = "employeeId") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.setChangePassToken(UUID.randomUUID().toString());
        String email = employee.getEmail();
        employeeService.saveEmployee(employee);
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom("Mail");
        passwordResetEmail.setTo(email);
        passwordResetEmail.setSubject("Mail Xác Nhận Đổi Mật Khẩu");
        passwordResetEmail.setText("Mã Xác Thực Của Bạn Là :" + employee.getChangePassToken());
        emailService.sendEmail(passwordResetEmail);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @CrossOrigin
    @ResponseBody
    @PutMapping(value = "/changePassword/{idEmployee}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public String changePasswordEpl(@RequestBody ChangePasswordEmployeeDTO changePasswordEmployeeDTO, @PathVariable(value = "idEmployee") Long id) {

        Employee employee = this.employeeService.findEmployeeById(id);
        String getTokenEmployee = employee.getChangePassToken();
        AppAccount appAccount = employee.getAppAccount();
        if (appAccount == null) {
            return "{\"result\" : 0}";
        }
        if(!changePasswordEmployeeDTO.getTokenCode().equals(getTokenEmployee)){
            return "{\"result\" : 2}";
        }
        if ((changePasswordEmployeeDTO.getPasswordOld()).equals(appAccount.getPassword()) && (changePasswordEmployeeDTO.getTokenCode().equals(getTokenEmployee))) {
            appAccount.setPassword(changePasswordEmployeeDTO.getPasswordNew());
            this.appAccountService.saveAppAccount(appAccount);
            return "{\"result\" : 1}";
        }

        else {
            return "{\"result\" : 0}";
        }
    }

}


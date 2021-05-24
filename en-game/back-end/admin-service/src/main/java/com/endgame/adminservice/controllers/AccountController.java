package com.endgame.adminservice.controllers;

import com.endgame.adminservice.securities.JwtTokenUtil;
import com.endgame.adminservice.sevices.EmployeeService;
import com.endgame.adminservice.sevices.MailService;
import com.endgame.adminservice.commons.FuncHelper;
import com.endgame.adminservice.dto.authentication.AccountDTOLogin;
import com.endgame.adminservice.dto.authentication.JwtResponse;
import com.endgame.adminservice.sevices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import javax.validation.Valid;

@RestController
//@CrossOrigin(origins = "http://localhost:4201", allowedHeaders = "*")
@RequestMapping("/api/admin")
public class AccountController {
    @Autowired
    private MailService mailService;
    @Autowired
    AccountService accountService;
    @Autowired
    EmployeeService employeeService;
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    FuncHelper funcHelper;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCustomers() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AccountDTOLogin accountDTOLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountDTOLogin.getUsername(), accountDTOLogin.getPassword())
        );
        UserDetails userDetails =
                accountService.loadUserByUsername(authentication.getName().toLowerCase());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), accountService.getAccountBasicInfo()));
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public ResponseEntity<?> signOut(@RequestBody AccountDTOLogin accountDTOLogin) {
        jwtTokenUtil.invalidateRelatedTokens(accountDTOLogin.getUsername());
        return null;
    }

    //    API Reset Password
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity<AccountDTOLogin> resetPassword(@Valid @RequestBody AccountDTOLogin accountDTOLogin) {
        funcHelper = new FuncHelper();
        String pass = funcHelper.getPassword();
        String email = employeeService.findEmailByUsername(accountDTOLogin.getUsername());
        mailService.sendEmail("End Game Project", email, "Username: " + accountDTOLogin.getUsername() +"\nPassword New: " + pass);
        accountDTOLogin.setPassword(passwordEncoder.encode(pass));
        accountService.resetPassword(accountDTOLogin);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOLogin);
    }

    //    API Change Password
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@Valid @RequestBody String[] password, Authentication authentication) {
        String username = authentication.getName();
        String pass = password[0];
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, pass)
        );
        AccountDTOLogin accountDTOLogin = new AccountDTOLogin();
        accountDTOLogin.setPassword(passwordEncoder.encode(password[1]));
        accountDTOLogin.setUsername(username);
        accountService.resetPassword(accountDTOLogin);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);

    }

    //    API Change Password
    @RequestMapping(value = "/get_user/{userName}", method = RequestMethod.GET)
    public UserDetails getUserById(@Valid @PathVariable String userName) {

        return accountService.loadUserByUsername(userName);
    }
}

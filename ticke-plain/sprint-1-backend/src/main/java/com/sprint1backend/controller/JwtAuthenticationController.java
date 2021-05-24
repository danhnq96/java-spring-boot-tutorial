package com.sprint1backend.controller;

import com.sprint1backend.config.JwtTokenUtil;
import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.model.*;
import com.sprint1backend.repository.AppAccountRepository;
import com.sprint1backend.service.account.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/*
Expose a POST API /authenticate using the JwtAuthenticationController. The POST API gets username and password in the
body- Using Spring Authentication Manager we authenticate the username and password.If the credentials are valid,
a JWT token is created using the JWTTokenUtil and provided to the client.
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    AppAccountRepository appAccountRepository;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    // API for testing
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok(new MessageDTO("Hello"));
    }

    // register new user
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody AccountDTO account, HttpServletRequest request) throws Exception {
        // prevent same email register again
        if (appAccountRepository.findByUsername(account.getUsername()) != null) {
            return ResponseEntity.ok(new MessageDTO("Email đã được đăng kí"));
        }

        String siteURL = request.getRequestURL().toString();
        return ResponseEntity.ok(userDetailsService.save(account, siteURL));
    }
    
    // authenticate current user
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        String username = authenticationRequest.getUsername();

        // check username exist
        if (appAccountRepository.findByUsername(username) == null) {
            return ResponseEntity.ok(new MessageDTO("Email chưa được đăng kí"));
        }

        // check if verification email is confirmed
        if (!appAccountRepository.findByUsername(username).getEnabled())
            return ResponseEntity.ok(new MessageDTO("Email chưa được kích hoạt"));

        authenticate(username, authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);

        AppAccount account = appAccountRepository.findByUsername(username);
        Long id = appAccountRepository.findByUsername(username).getId();
        // find information to return based on account role 
        String role = account.getAppRole().getName();
        String fullName;
        switch (role) {
            case "Admin":
                fullName = account.getAppAdmin().getFullName();
                break;
            case "Employee":
                fullName = account.getEmployee().getFullName();
                break;
            default:
                fullName = account.getAppUser().getFullName();
        }

        return ResponseEntity.ok(new JwtResponseNew(id, username, token, fullName, role));
    }

    // method that do the authentication process
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
    // handle confirmation request when user click on verification link
    @GetMapping("/register/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam String code) {
        boolean verify = userDetailsService.verify(code);
        if (verify) {
            return ResponseEntity.ok(new MessageDTO("Verification Succeed!"));
        } else {
            return ResponseEntity.ok(new MessageDTO("Verification Fail"));
        }
    }
    
    // handle authentication with google account
    @PostMapping("/login/google")
    public ResponseEntity<?> loginGoogle(@RequestBody AccountDTO account) throws Exception {
        String username = account.getUsername();
        
        // create new account for google user if none exists
        if (appAccountRepository.findByUsername(username) == null) {
            userDetailsService.saveGoogle(account);
        }

        authenticate(username, account.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(account.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        Long id = appAccountRepository.findByUsername(username).getId();
        String fullName = appAccountRepository.findByUsername(username).getAppUser().getFullName();

        return ResponseEntity.ok(new JwtResponseNew(id, username, token, fullName, "User"));

    }
}

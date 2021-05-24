package com.sprint1backend.service.account;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.AppUser;
import com.sprint1backend.model.AccountDTO;
import com.sprint1backend.repository.AppAccountRepository;
import com.sprint1backend.repository.AppRoleRepository;
import com.sprint1backend.service.user.AppUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/*
JWTUserDetailsService implements the Spring Security UserDetailsService interface.
It overrides the loadUserByUsername for fetching user details from the database using the username.
The Spring Security Authentication Manager calls this method for getting the user details from the database
when authenticating the user details provided by the user. Here we are getting the user details from a hardcoded
User List. In the next tutorial we will be adding the DAO implementation for fetching User Details from the Database.
Also the password for a user is stored in encrypted format using BCrypt.
Previously we have seen Spring Boot Security - Password Encoding Using Bcrypt.
Here using the Online Bcrypt Generator you can generate the Bcrypt for a password.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppAccount user = appAccountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public AppAccount save(AccountDTO account, String siteURL) throws UnsupportedEncodingException, MessagingException {
        // convert accountDTO to account entity
        AppAccount newAccount = new AppAccount();
        newAccount.setUsername(account.getUsername());
        newAccount.setPassword(bcryptEncoder.encode(account.getPassword()));
        newAccount.setAppRole(appRoleRepository.findByName("User"));

        // convert userDTO to user entity
        AppUser appUser = appUserService.convertAppUserDTO(account.getAppUser());
        appUser.setAppAccount(newAccount);
        newAccount.setAppUser(appUser);

        // generate verification code for confirming later
        String randomCode = RandomString.make(64);
        newAccount.setVerificationCode(randomCode);
        appAccountRepository.save(newAccount);
        sendVerificationEmail(newAccount, siteURL);
        return newAccount;
    }

    // method that send the confirmation code to user email after register
    public void sendVerificationEmail(AppAccount newUser, String siteURL) throws UnsupportedEncodingException, MessagingException {
        // define email subject, sender name
        String subject = "Vui lòng xác nhận email";
        String senderName = "C06 Airline";

        // define email content
        String mailContent = "<p>Dear " + newUser.getUsername() + ",</p>";
        mailContent += "<p>Vui lòng click vào đường link phía dưới để xác nhận đăng kí</p>";

        // embed verification code; siteURL is the url that handle register process,
        // in this case: siteURL = localhost:4200/register
        String verifyURL = siteURL + "/verify?code=" + newUser.getVerificationCode();
        mailContent += "<a href=\"" + verifyURL + "\">Xác nhận</a>";
        mailContent += "<p>Trân trọng, C06 Airline Team</p>";

        // message helper to add information set previously into email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true, "utf-8");

        helper.setFrom("kaelboom92@gmail.com", senderName);
        helper.setTo(newUser.getUsername());
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }

    // method for handling confirmation request
    public boolean verify(String verificationCode) {
        AppAccount account = appAccountRepository.findByVerificationCode(verificationCode);
        if (account == null || account.getEnabled()) {
            return false;
        } else {
            account.setEnabled(true);
            appAccountRepository.save(account);
            return true;
        }
    }

    // method for handling google account request
    public AppAccount saveGoogle(AccountDTO account) {
        AppAccount newAccount = new AppAccount();
        newAccount.setUsername(account.getUsername());
        newAccount.setPassword(bcryptEncoder.encode(account.getPassword()));
        newAccount.setAppRole(appRoleRepository.findByName("User"));

        AppUser appUser = appUserService.convertAppUserDTO(account.getAppUser());
        appUser.setAppAccount(newAccount);
        newAccount.setAppUser(appUser);
        appAccountRepository.save(newAccount);
        return newAccount;
    }
}

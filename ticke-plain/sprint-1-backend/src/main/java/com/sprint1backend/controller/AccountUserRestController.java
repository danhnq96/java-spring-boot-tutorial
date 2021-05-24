package com.sprint1backend.controller;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.model.ChangePasswordDTO;
import com.sprint1backend.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change")
@CrossOrigin
public class AccountUserRestController {

    @Autowired
    AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping(value = "/{id}/password")
    public ResponseEntity<?> changePassWordUser( @RequestBody ChangePasswordDTO changePasswordDTO,
                                                @PathVariable("id") long id) {
        AppAccount appAccount = accountService.findById(id);

        if (appAccount == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (BCrypt.checkpw(changePasswordDTO.getOldPassword(), appAccount.getPassword())) {
            accountService.changePassword(id, passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}

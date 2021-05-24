package com.sprint1backend.controller;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.AppUser;
import com.sprint1backend.model.UserUpdateDTO;
import com.sprint1backend.repository.AppAccountRepository;
import com.sprint1backend.repository.AppUserRepository;
import com.sprint1backend.service.account.AccountService;
import com.sprint1backend.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestController {
    @Autowired
    AppUserService appUserService;
    @Autowired
    AccountService accountService;
    @Autowired
    AppAccountRepository appAccountRepository;
    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping()
    public ResponseEntity<List<AppUser>> getAppUserList() {
        List<AppUser> appUserList = appUserService.findAppUserAll();
        if (appUserList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else
        return new ResponseEntity<>(appUserList, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<AppUser> getAppUserByID(@PathVariable Long id) {
        AppAccount account = this.appAccountRepository.findById(id).orElse(null);
        AppUser appUser = this.appUserRepository.findByAppAccount(account);
        if (appUser ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@Validated @PathVariable("id") long id, @RequestBody UserUpdateDTO userUpdateDTO, BindingResult bindingResult) {
        AppAccount account = this.appAccountRepository.findById(id).orElse(null);
        AppUser appUser = this.appUserRepository.findByAppAccount(account);
        if (appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        appUser.setFullName(userUpdateDTO.getFullName());
        appUser.setBirthday(userUpdateDTO.getBirthday());
        appUser.setAddress(userUpdateDTO.getAddress());
        appUser.setEmail(userUpdateDTO.getEmail());
        appUser.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        appUser.setGender(userUpdateDTO.getGender());
        appUserService.save(appUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PatchMapping("/{id}/change-password")


    }


package com.sprint1backend.controller;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class AppAccountController {
    @Autowired
    AccountService appAccountService;
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public AppAccount getCustomerById(@PathVariable("accountId") Long id) {
        return appAccountService.findByIdAppAccount(id);
    }
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void changePassword(@PathVariable("accountId") Long id, @RequestBody AppAccount accountForm) {
//        this.appAccountService.save(accountForm,id);
    }

}

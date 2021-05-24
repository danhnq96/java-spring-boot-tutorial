package com.csf.whoami.controller.api;

import java.util.Base64;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.whoami.util.MessageUtil;

@RestController
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        String originalInput = "csf";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        String databaseInput = "whoami";
        String encodedDB = Base64.getEncoder().encodeToString(databaseInput.getBytes());
        System.out.println("Encoding: " + encodedString);
        System.out.println("Encoding DB: " + encodedDB);
        return "Message are: " + MessageUtil.getMessage("message.loginfail");
    }

}
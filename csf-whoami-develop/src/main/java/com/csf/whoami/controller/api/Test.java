package com.csf.whoami.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

    @GetMapping(value = "/home/abc")
    public String goHome() {
        return "/views/admin/template-management/temp-register";
    }
}

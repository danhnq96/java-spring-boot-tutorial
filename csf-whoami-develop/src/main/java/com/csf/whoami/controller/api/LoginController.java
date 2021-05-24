package com.csf.whoami.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csf.whoami.constant.UrlConstants;

@Controller
public class LoginController {

//    @Autowired
//    private AdminService adminService;

    @GetMapping(value = UrlConstants.URI_ADMIN_LOGIN)
    @ResponseBody
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView(UrlConstants.URI_USER_LOGIN); // thymeleaf
        return modelAndView;
    }

    @GetMapping(value = {"/admin/home", "/admin/index"})
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}

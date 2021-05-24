package com.csf.whoami.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csf.whoami.converter.AdminDomain;
import com.csf.whoami.database.TbAdminRole;
import com.csf.whoami.dto.JwtRequest;
import com.csf.whoami.security.JwtTokenUtil;
import com.csf.whoami.service.AdminRoleService;
import com.csf.whoami.service.AdminService;
import com.csf.whoami.util.LogUtil;
import com.csf.whoami.util.MessageUtil;


@RestController
public class APILoginController extends AbstractController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

//    @Autowired
//    private MessageSource messageSource;

    @Value("${databasekey}")
    private String databasekey;

//    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
//    public int login(@RequestBody TbAdmin admin, HttpServletRequest request) throws Exception{
//        int a = 0;
//        TbAdmin adminUser = adminService.findByNameAndPassword(admin.getName(), WoongjinCipher.Encrypt(admin.getPassword()));
//
//        if(adminUser!=null) {
//            request.getSession().setAttribute("TbAdmin", adminUser);
//            return a = 1;
//        }else{
//            return a = 2;
//        }
//    }


//    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
//    public ResponseEntity<TokenReponse> login(@RequestBody TbAdmin admin, HttpServletRequest request) throws Exception {
//
//        TbAdmin adminUser = adminService.findByNameAndPassword(admin.getName(), WoongjinCipher.Encrypt(admin.getPassword()));
//
//        String token = adminService.createToken(adminUser);
//        return ResponseEntity.ok().body(new TokenReponse(token,"barer"));
//
//    }


    @PostMapping(value = "/adminLogin")
    public String login(@RequestBody JwtRequest jwtRequest, HttpServletRequest request) throws Exception {

        AdminDomain admin = adminService.findByNameAndPassword(jwtRequest.getName(), jwtRequest.getPassword());

        if (admin == null) {
            LogUtil.logError(this, MessageUtil.getMessage("message.loginfail"));
            throw new Exception(MessageUtil.getMessage("message.loginfail"));
        }

        TbAdminRole adminRole = adminRoleService.findByTbAdminId(admin.getId());

        final String jwt = jwtTokenUtil.generateToken(admin);
        admin.setToken(jwt);

        adminService.tokenUpdate(admin);

        HttpSession session = request.getSession(true);
        session.setAttribute("admin", admin);
        session.setAttribute("adminRole", adminRole);

        return jwt;
    }

    @GetMapping(value = "/admin/logout")
    public ModelAndView logout(ModelAndView model, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        session.invalidate();

        model.setViewName("login");

        return model;
    }
}

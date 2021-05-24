package com.csf.whoami.security.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.csf.whoami.database.TbAdmin;
import com.csf.whoami.security.UserPrincipal;
import com.csf.whoami.service.AdminService;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbAdmin optionalUser = adminService.findByName(username);
        if (optionalUser != null) {
            return new UserPrincipal(optionalUser);
        }
        throw new UsernameNotFoundException("TbAdmin not found with username: " + username);
    }
}
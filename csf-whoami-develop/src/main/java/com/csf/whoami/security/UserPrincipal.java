package com.csf.whoami.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.csf.whoami.database.TbAdmin;

public class UserPrincipal implements UserDetails {

    @SuppressWarnings("unused")
    private TbAdmin user;

    public UserPrincipal(TbAdmin user) {
        this.user = user;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_";// + user.getPrivilege().toUpperCase();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";//user.getPassword();
    }

    @Override
    public String getUsername() {
        return "";//user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
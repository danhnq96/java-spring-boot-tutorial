/**
 *
 */
package com.csf.whoami.backend.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class UserInfo extends User {

    @Getter
    private String userId;

    public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        // TODO Auto-generated constructor stub
    }

    public UserInfo(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = String.valueOf(userId);
    }

    private static final long serialVersionUID = 1L;
}

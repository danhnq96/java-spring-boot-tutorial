package com.csf.whoami.common.domain;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class SignUpRequestDomain {

    @NotBlank
    @Length(min = 6, max = 32)
    private String username;

    @NotBlank
    @Length(min = 6, max = 32)
    private String password;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

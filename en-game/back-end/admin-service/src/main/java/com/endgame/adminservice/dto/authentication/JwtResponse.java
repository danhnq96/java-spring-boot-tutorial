package com.endgame.adminservice.dto.authentication;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String username;
    private AccountBasicInfoDTO accountBasicInfoDTO;



    public JwtResponse(String jwttoken, String username, AccountBasicInfoDTO accountBasicInfoDTO) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.accountBasicInfoDTO = accountBasicInfoDTO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountBasicInfoDTO getAccountBasicInfoDTO() {
        return accountBasicInfoDTO;
    }

    public void setAccountBasicInfoDTO(AccountBasicInfoDTO accountBasicInfoDTO) {
        this.accountBasicInfoDTO = accountBasicInfoDTO;
    }

    public String getToken() {
        return this.jwttoken;
    }
}

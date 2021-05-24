package com.endgame.adminservice.dto.employee;

public class AccountDTO {
    private String username;
    private String lastLogin;

    public AccountDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}

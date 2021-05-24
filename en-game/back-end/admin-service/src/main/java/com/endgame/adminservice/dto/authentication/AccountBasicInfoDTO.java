package com.endgame.adminservice.dto.authentication;

import com.endgame.adminservice.dto.employee.EmployeeBasicInfoDTO;

public class AccountBasicInfoDTO {
    private String username;
    private String lastLogin;

    private EmployeeBasicInfoDTO employee;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EmployeeBasicInfoDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeBasicInfoDTO employee) {
        this.employee = employee;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}

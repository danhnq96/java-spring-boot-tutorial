package com.sprint1backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String employeeCode;
    private String fullName;
    private String birthday;
    private String email;
    private Boolean gender;
    private String phoneNumber;
    private String password;
    private String role;
}

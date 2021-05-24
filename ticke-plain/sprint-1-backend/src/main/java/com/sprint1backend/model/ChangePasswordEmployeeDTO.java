package com.sprint1backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordEmployeeDTO {
    private String passwordOld;
    private String passwordNew;
    private String confirmPassword;
    private String tokenCode;
    private String message;
}
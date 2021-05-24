package com.endgame.adminservice.dto.employee;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class EmployeeExportExcelDTO {
    private String firstName;
    private String midName;
    private String lastName;
    private String address;
    private String birthday;
    private String image;
    private Boolean active;
    private String startDate;
    private int gender;
    private String email;
    private String idCard;
    private String phone;
    private AccountDTO account;

}

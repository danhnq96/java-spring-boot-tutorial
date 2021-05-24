package com.endgame.adminservice.dto.member;

import com.endgame.adminservice.dto.memberType.MemberTypeExcelDTO;

public class MemberExportExcelDTO {
    private String email;
    private String firstName;
    private String midName;
    private String lastName;
    private String address;
    private String birthday;
    private String image;
    private Boolean active;
    private String lastLogin;
    private String registerDate;
    private int gender;
    private String phone;
    private MemberTypeExcelDTO memberType;
}

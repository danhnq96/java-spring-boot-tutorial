package com.csf.whoami.dto;

import java.util.Date;

import com.csf.whoami.constant.CommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfo {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String passwordConfirm;
    private String phone;
    private Long roleId;
    private String rolename;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = CommonConstants.TIMEZONE)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = CommonConstants.TIMEZONE)
    private Date updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = CommonConstants.TIMEZONE)
    private Date deletedAt;

    public AdminInfo(Long id, String rolename, Long roleId, String name, String email, String username, Date createdAt) {
        this.id = id;
        this.rolename = rolename;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
    }

    public AdminInfo(Long id, String rolename, Long roleId, String name, String email, String username, Date createdAt, String password, String passwordConfirm) {
        this.id = id;
        this.rolename = rolename;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}

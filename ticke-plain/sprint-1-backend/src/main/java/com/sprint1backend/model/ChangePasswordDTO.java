package com.sprint1backend.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ChangePasswordDTO {
    private long id;
    private String userName;
    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Pattern(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$/", message = "Mật khẩu không hợp lệ")
    private String oldPassword;
    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Pattern(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$/", message = "Mật khẩu không hợp lệ")
    private String newPassword;
    private String message;

    public ChangePasswordDTO(long id, String userName, String oldPassword, String newPassword) {
        this.id = id;
        this.userName = userName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePasswordDTO(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
